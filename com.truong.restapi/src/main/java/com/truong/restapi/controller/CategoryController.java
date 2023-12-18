package com.truong.restapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.truong.common.exception.CustomException;
import com.truong.common.utils.BaseResponse;
import com.truong.entity.Category;
import com.truong.entity.Employee;
import com.truong.restapi.request.CategoryRequest;
import com.truong.restapi.response.CategoryResponse;
import com.truong.service.CategoryService;

@RestController
@RequestMapping("api/v1/caterories")
public class CategoryController extends BaseController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("")
	public ResponseEntity<BaseResponse<List<CategoryResponse>>> getAll(
			@RequestParam(name = "category_type", defaultValue = "-1") int categoryType,
			@RequestParam(name = "status", defaultValue = "-1") int status,
			@RequestParam(name = "keySearch", defaultValue = "") String keySearch) throws Exception {

		BaseResponse<List<CategoryResponse>> response = new BaseResponse<>();

		if (this.getUser() == null) {
			response.setStatus(HttpStatus.UNAUTHORIZED);
			response.setMessage(HttpStatus.UNAUTHORIZED);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		List<Category> listCategories = categoryService.getList(categoryType, status, keySearch);

		response.setData(new CategoryResponse().mapToList(listCategories));

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("{id}")
	public ResponseEntity<BaseResponse<CategoryResponse>> getDetail(@PathVariable(name = "id") int id)
			throws Exception {

		BaseResponse<CategoryResponse> response = new BaseResponse<>();

		if (this.getUser() == null) {
			response.setStatus(HttpStatus.UNAUTHORIZED);
			response.setMessage(HttpStatus.UNAUTHORIZED);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Category detail = categoryService.getDetail(id);

		response.setData(new CategoryResponse(detail));

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("{name}")
	public ResponseEntity<BaseResponse<CategoryResponse>> findByName(@PathVariable(name = "name") String name)
			throws Exception {
		BaseResponse<CategoryResponse> response = new BaseResponse<>();

		if (this.getUser() == null) {
			response.setStatus(HttpStatus.UNAUTHORIZED);
			response.setMessage(HttpStatus.UNAUTHORIZED);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Category category = categoryService.findByName(name);

		response.setData(new CategoryResponse(category));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("create")

	public ResponseEntity<BaseResponse<CategoryResponse>> create(@Valid @RequestBody CategoryRequest categoryRequest)
			throws Exception {
		BaseResponse<CategoryResponse> response = new BaseResponse<>();

		Employee employee = this.getUser();

		if (employee == null) {
			response.setStatus(HttpStatus.UNAUTHORIZED);
			response.setMessage(HttpStatus.UNAUTHORIZED);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Category category = new Category();
		category.setName(categoryRequest.getName());
		category.setDescription(categoryRequest.getDescription());
		category.setCategoryType(categoryRequest.getCategoryType());

		response.setData(new CategoryResponse(categoryService.create(category)));

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}

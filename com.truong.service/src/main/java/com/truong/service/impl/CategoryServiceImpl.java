package com.truong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truong.common.exception.CustomException;
import com.truong.dao.CategoryDao;
import com.truong.entity.Category;
import com.truong.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao dao;

	@Override
	public Category create(Category category) throws Exception {
		return dao.create(category);
	}

	@Override
	public void update(Category category) {
		dao.update(category);

	}

	@Override
	public List<Category> getList(int categoryType, int status, String name) throws Exception {
		return dao.getList(categoryType, status, name);
	}

	@Override
	public Category findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public Category getDetail(int id) {
		return dao.getDetail(id);
	}

}

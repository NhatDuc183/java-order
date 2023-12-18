package com.truong.service;

import java.util.List;

import com.truong.common.exception.CustomException;
import com.truong.entity.Category;

public interface CategoryService {

	public Category create(Category category) throws Exception;

	public void update(Category category);

	public Category getDetail(int id);

	public List<Category> getList(int categoryType, int status, String name) throws Exception;

	public Category findByName(String name);

}

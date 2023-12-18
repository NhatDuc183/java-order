package com.truong.dao;

import java.util.List;

import com.truong.common.exception.CustomException;
import com.truong.entity.Category;
import com.truong.entity.Post;

public interface CategoryDao {

	public Category create(Category category) throws Exception;

	public void update(Category category);

	public Category getDetail(int id);

	public List<Category> getList(int categoryType, int status, String name) throws Exception;

	public Category findByName(String name);
}

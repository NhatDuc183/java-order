package com.truong.restapi.request;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryRequest {

	@NotEmpty(message = "Tên danh mục không được để trống")
	@NotNull(message = "Tên danh mục không được null")
	public String name;

//	@NotEmpty(message = "Loại danh mục không được để trống")
//	@Column(name = "category_type")
	public int categoryType;

	public String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

package com.truong.restapi.response;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.truong.entity.Category;

public class CategoryResponse {
	private int id;

	private String name;

	private String description;

	@JsonProperty("category_type")
	private int categoryType;

	private boolean status;

	@JsonProperty("created_at")
	private Date createdAt;

	@JsonProperty("update_at")
	private Date updateAt;

	/**
	 * 
	 */
	public CategoryResponse() {
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param categoryType
	 * @param status
	 * @param createdAt
	 * @param updateAt
	 */
	public CategoryResponse(Category category) {
		super();
		this.id = category.getId();
		this.name = category.getName();
		this.description = category.getDescription();
		this.categoryType = category.getCategoryType();
		this.status = category.isStatus();
		this.createdAt = category.getCreatedAt();
		this.updateAt = category.getUpdateAt();
	}

	public List<CategoryResponse> mapToList(List<Category> entities) {
		return entities.stream().map(e -> new CategoryResponse(e)).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = Calendar.getInstance().getTime();
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

}

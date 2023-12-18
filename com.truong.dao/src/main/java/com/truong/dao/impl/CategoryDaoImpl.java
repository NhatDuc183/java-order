package com.truong.dao.impl;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.truong.common.exception.CustomException;
import com.truong.dao.AbstractDao;
import com.truong.dao.CategoryDao;
import com.truong.entity.Category;
import com.truong.entity.custom.StoreProcedureStatusCodeEnum;

@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl extends AbstractDao<Integer, Category> implements CategoryDao {

	@Override
	public Category create(Category category) throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_create_category", Category.class)
				.registerStoredProcedureParameter("name", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("description", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("category_type", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("name", category.getName());
		query.setParameter("description", category.getDescription());
		query.setParameter("category_type", category.getCategoryType());

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS: {
//			return (Category) query.getResultList().stream().findFirst().orElse(null);
			return category;
		}
		case INPUT_INVALID: {

			throw new CustomException(HttpStatus.BAD_REQUEST, messageError);

		}
		default:
			throw new Exception(messageError);
		}
	}

	@Override
	public void update(Category category) {
		this.getSession().update(category);

	}

	@Override
	public List<Category> getList(int categoryType, int status, String keysearch) throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_get_categories", Category.class)
				.registerStoredProcedureParameter("category_type", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("keysearch", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("category_type", categoryType);
		query.setParameter("status", status);
		query.setParameter("keysearch", keysearch);
		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return query.getResultList();
		case INPUT_INVALID:
			throw new CustomException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}

	}

	@Override
	public Category findByName(String name) {
		return this.getSession().find(Category.class, name);
	}

	@Override
	public Category getDetail(int id) {
		return this.getSession().find(Category.class, id);
	}

}

package com.truong.dao;

import java.util.List;

import com.truong.common.exception.CustomException;
import com.truong.entity.Post;

public interface PostDao {
	
	public void update(Post post);
	
	public Post findOne(int id);
	
	public List<Post> findAll(int status) throws Exception;

	public Post spCreatePost(Post post) throws Exception;
}

package com.rossi21.familyPlanner.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rossi21.familyPlanner.Models.Comment;

public interface CommentRepository  extends CrudRepository<Comment, Long> {

	List<Comment> findAll();
}

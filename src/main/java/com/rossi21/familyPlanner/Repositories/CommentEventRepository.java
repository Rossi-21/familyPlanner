package com.rossi21.familyPlanner.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rossi21.familyPlanner.Models.CommentEvent;

@Repository
public interface CommentEventRepository extends CrudRepository<CommentEvent, Long> {
	List<CommentEvent> findAll();
}

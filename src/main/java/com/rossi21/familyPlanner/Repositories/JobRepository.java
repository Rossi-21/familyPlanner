package com.rossi21.familyPlanner.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rossi21.familyPlanner.Models.Job;
import com.rossi21.familyPlanner.Models.User;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

	List<Job> findAll();
	Optional<Job> findByName(String title);
	List<Job> findAllByUsers(User user);
	List<Job> findByUsersNotContains(User user);
}

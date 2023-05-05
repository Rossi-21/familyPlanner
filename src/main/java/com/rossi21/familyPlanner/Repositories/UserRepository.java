package com.rossi21.familyPlanner.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rossi21.familyPlanner.Models.Event;
import com.rossi21.familyPlanner.Models.Job;
import com.rossi21.familyPlanner.Models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findAll();
	Optional<User> findByEmail(String email);
	List<User> findAllByJobs(Job job);
	List<User> findByJobsNotContains(Job job);
	List<User> findAllByEvents(Event event);
	List<User> findByEventsNotContains(Event event);
}

package com.rossi21.familyPlanner.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rossi21.familyPlanner.Models.Event;
import com.rossi21.familyPlanner.Models.User;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

	List<Event> findAll();
	Optional<Event> findByName(String title);
	List<Event> findAllByUsers(User user);
	List<Event> findByUsersNotContains(User user);
}

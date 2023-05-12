package com.rossi21.familyPlanner.Services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rossi21.familyPlanner.Models.Event;
import com.rossi21.familyPlanner.Models.Job;
import com.rossi21.familyPlanner.Models.User;
import com.rossi21.familyPlanner.Repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepo;
	
	// Create
	public Event createEvent(Event e) {
		
		return eventRepo.save(e);
	}
	
	// retrieve one
	public Event getOneById(Long id) {
		Optional<Event> event =eventRepo.findById(id);
		if (event.isEmpty()) {
			return null;
		}
		
		return event.get();
	}
	// returns all 
    public List<Event> allEvents() {
    	
        return eventRepo.findAll();
    }
    
    // update 
    public Event updateEvent (Event e) {
    
		return eventRepo.save(e);
	}
    
    // delete 
	public void deleteEvent (Long id) {
		
		eventRepo.deleteById(id);
	}
	
	//Get Events Assigned to User
	public List<Event> getAssignedEvents(User user){
		return eventRepo.findAllByUsers(user);
	}
	
	//Get Events Not Assigned to User
	public List<Event> getUnassignedEvents(User user){
		return eventRepo.findByUsersNotContains(user);
	}
	
	//Get Events Assigned to User Sorted by Date
	public List<Event> getAssignedEventsSortedByDate(User user) {
	    List<Event> assignedEvents = getAssignedEvents(user);
	    assignedEvents.sort(Comparator.comparing(Event::getDate));
	    return assignedEvents;
	}
	
	//Get Events Assigned to User Sorted by Date
	public List<Event> getUnAssignedEventsSortedByDate(User user) {
	    List<Event> unassignedEvents = getUnassignedEvents(user);
	    unassignedEvents.sort(Comparator.comparing(Event::getDate));
	    return unassignedEvents;
	}
}

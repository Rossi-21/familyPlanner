package com.rossi21.familyPlanner.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rossi21.familyPlanner.Models.CommentEvent;
import com.rossi21.familyPlanner.Models.Event;
import com.rossi21.familyPlanner.Models.User;
import com.rossi21.familyPlanner.Services.CommentEventService;
import com.rossi21.familyPlanner.Services.EventService;
import com.rossi21.familyPlanner.Services.UserService;

@Controller
public class EventController {

	@Autowired
    private UserService userServ;
	@Autowired
	private EventService eventServ;
	@Autowired
	private CommentEventService comEventServ;
	
	//Event Home Page
	@GetMapping("/thefamilyplanner/events")
    public String home(Model model, @ModelAttribute("event") Event event, HttpSession session) {
    	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
    	Long userId = (Long)session.getAttribute("userId");
    	model.addAttribute("user", userServ.getOneById(userId));
    	User user = userServ.getOneById(userId);
    	model.addAttribute("user", user);
    	
    	List<Event> events = eventServ.getUnAssignedEventsSortedByDate(user);
    	model.addAttribute("avalibleEvents", events);
    	
        List<Event> sortedEvents = eventServ.getAssignedEventsSortedByDate(user);
        model.addAttribute("myEvents", sortedEvents);
    	
    	return "events.jsp";
    }
	
	// Create a event page
	@GetMapping("/thefamilyplanner/events/new")
    public String createEventPage(Model model, @ModelAttribute("event") Event event,  HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		List<User> users = userServ.allUsers();
		model.addAttribute("users", users);
		Long userId = (Long)session.getAttribute("userId");
    	model.addAttribute("user", userServ.getOneById(userId));
    	
        return "newEvent.jsp";
    }
	
	// Create a event method
	@PostMapping("/thefamilyplanner/events/new")
	public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	List<User> users = userServ.allUsers();
	        model.addAttribute("users", users);
            return "newEvent.jsp";
        } else {
        	eventServ.createEvent(event);
            return "redirect:/thefamilyplanner/events";
        }	
	}
	
	// Display a event page
	@GetMapping("/thefamilyplanner/events/{id}")
    public String show(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		Event event = eventServ.getOneById(id);
        model.addAttribute("event", event);
        Long userId = (Long)session.getAttribute("userId");
    	model.addAttribute("user", userServ.getOneById(userId));
    	List<CommentEvent> commentEvents = comEventServ.allCommentEvents();
        model.addAttribute("commentEvents", commentEvents);
    	
        return "showEvent.jsp";
    }
	
	// Edit an event page
	@GetMapping("/thefamilyplanner/events/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		List<User> users = userServ.allUsers();
		model.addAttribute("users", users);
		Event event = eventServ.getOneById(id);
        model.addAttribute("event", event);
        List<User> myUsers = userServ.getAssignedUsersEvent(event);
		model.addAttribute("myUsers", myUsers);
        
        return "editEvent.jsp";
    }
	
	// Update an event method
	@RequestMapping(value="/thefamilyplanner/events/{id}/edit", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("event") Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	List<User> users = userServ.allUsers();
	        model.addAttribute("users", users);
	        List<User> myUsers = userServ.getAssignedUsersEvent(event);
	        model.addAttribute("myUsers", myUsers);
            return "editEvent.jsp";
        } else {
        	eventServ.updateEvent(event);
            return "redirect:/thefamilyplanner/events";
        }
    }
	
	//Event Comment Page
	@GetMapping("/thefamilyplanner/events/{id}/comment")
    public String comment(@PathVariable("id") Long id, Model model, @ModelAttribute("commentEvent") CommentEvent commentEvent, HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		Event event = eventServ.getOneById(id);
        model.addAttribute("event", event);
        Long userId = (Long)session.getAttribute("userId");
    	model.addAttribute("user", userServ.getOneById(userId));
    	List<CommentEvent> commentEvents = comEventServ.allCommentEvents();
        model.addAttribute("commentEvents", commentEvents);
        
        return "commentEvent.jsp";
	}
	
	//Event Comment Method
	@PostMapping("/events/comment")
	public String createComment(@Valid @ModelAttribute("commentEvent") CommentEvent commentEvent, BindingResult result) {
        if (result.hasErrors()) {
            return "commentEvent.jsp";
        } else {
        	comEventServ.createCommentEvent(commentEvent);
        	return String.format("redirect:/thefamilyplanner/events/%s/comment", Long.toString(commentEvent.getEvent().getId()) );
        }	
	}
	
	// Delete a job method
	@DeleteMapping("/events/{id}")
    public String destroy(@PathVariable("id") Long id) {
		eventServ.deleteEvent(id);
        return "redirect:/thefamilyplanner/events";
    }
}

package com.rossi21.familyPlanner.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.rossi21.familyPlanner.Models.Event;
import com.rossi21.familyPlanner.Services.EventService;
import com.rossi21.familyPlanner.Services.UserService;

@Controller
public class EventController {

	@Autowired
    private UserService userServ;
	@Autowired
	private EventService eventServ;
	
	@GetMapping("/thefamilyplanner/events")
    public String home(Model model, @ModelAttribute("event") Event event, HttpSession session) {
    	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
    	Long userId = (Long)session.getAttribute("userId");
    	model.addAttribute("user", userServ.getOneById(userId));
    	
    	return "events.jsp";
    }
}

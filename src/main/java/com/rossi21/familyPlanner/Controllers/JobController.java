package com.rossi21.familyPlanner.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rossi21.familyPlanner.Models.Job;
import com.rossi21.familyPlanner.Models.User;
import com.rossi21.familyPlanner.Services.JobService;
import com.rossi21.familyPlanner.Services.UserService;

@Controller
public class JobController {

	@Autowired
    private UserService userServ;
	@Autowired
	private JobService jobServ;
	
	@GetMapping("/thefamilyplanner/jobs")
    public String jobsHome(Model model, @ModelAttribute("job") Job job, HttpSession session) {
    	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	List<Job> jobs = jobServ.allJobs();
    	model.addAttribute("jobs", jobs);
    	Long userId = (Long)session.getAttribute("userId");
    	model.addAttribute("user", userServ.getOneById(userId));
    	
    	return "jobs.jsp";
    }
	
	// Create a job page
	@GetMapping("/thefamilyplanner/jobs/new")
    public String createJobPage(Model model, @ModelAttribute("job") Job job,  HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		List<User> users = userServ.allUsers();
		model.addAttribute("users", users);
		Long userId = (Long)session.getAttribute("userId");
    	model.addAttribute("user", userServ.getOneById(userId));
    	
        return "newJob.jsp";
    }
	
	// Create a job method
	@PostMapping("/jobs/create")
	public String createJob(@Valid @ModelAttribute("job") Job job, BindingResult result) {
        if (result.hasErrors()) {
            return "newJob.jsp";
        } else {
        	jobServ.createJob(job);
            return "redirect:/thefamilyplanner/jobs";
        }	
	}
}

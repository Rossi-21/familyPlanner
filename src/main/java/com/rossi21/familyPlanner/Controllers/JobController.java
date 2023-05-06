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
    	
    	Long userId = (Long)session.getAttribute("userId");
    	model.addAttribute("user", userServ.getOneById(userId));
    	User user = userServ.getOneById(userId);
    	model.addAttribute("user", user);
    	List<Job> myJob = jobServ.getAssignedJobs(user);
    	model.addAttribute("myJobs", myJob);
		List<Job> jobs = jobServ.getUnassignedJobs(user);
    	model.addAttribute("avalibleJobs", jobs);
    	
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
	
	// Display a Job Page
	@GetMapping("/thefamilyplanner/jobs/{id}")
    public String show(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		Job job = jobServ.getOneById(id);
        model.addAttribute("job", job);
        Long userId = (Long)session.getAttribute("userId");
    	model.addAttribute("user", userServ.getOneById(userId));
    	
        return "showJob.jsp";
    }
	
	// Edit a job page
	@GetMapping("/thefamilyplanner/jobs/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		List<User> users = userServ.allUsers();
		model.addAttribute("users", users);
		Job job = jobServ.getOneById(id);
        model.addAttribute("job", job);
        List<User> myUsers = userServ.getAssignedUsers(job);
		model.addAttribute("myUsers", myUsers);
        
        return "editJob.jsp";
    }
	
	// Update a job method
	@RequestMapping(value="/jobs/{id}", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("job") Job job, BindingResult result) {
        if (result.hasErrors()) {
            return "editJob.jsp";
        } else {
        	jobServ.updateJob(job);
            return "redirect:/thefamilyplanner/jobs";
        }
    }
	
	// Delete a project method
	@DeleteMapping("/jobs/{id}")
    public String destroy(@PathVariable("id") Long id) {
		jobServ.deleteJob(id);
        return "redirect:/jobs";
    }
}

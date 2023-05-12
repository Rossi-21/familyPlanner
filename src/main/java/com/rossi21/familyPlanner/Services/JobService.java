package com.rossi21.familyPlanner.Services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rossi21.familyPlanner.Models.Job;
import com.rossi21.familyPlanner.Models.User;
import com.rossi21.familyPlanner.Repositories.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepo;
	
	// Create
	public Job createJob(Job j) {
		
		return jobRepo.save(j);
	}
	
	// retrieve one
	public Job getOneById(Long id) {
		Optional<Job> job =jobRepo.findById(id);
		if (job.isEmpty()) {
			return null;
		}
		
		return job.get();
	}
	// returns all 
    public List<Job> allJobs() {
    	
        return jobRepo.findAll();
    }
    
    // update 
    public Job updateJob (Job j) {
    	
		return jobRepo.save(j);
	}
    
    // delete 
	public void deleteJob (Long id) {
		
		jobRepo.deleteById(id);
	}
	
	//Get Jobs Assigned to User
	public List<Job> getAssignedJobs(User user){
		return jobRepo.findAllByUsers(user);
	}
	
	//Get Jobs not Assigned to User
	public List<Job> getUnassignedJobs(User user){
		return jobRepo.findByUsersNotContains(user);
	}
	
	//Get Jobs Assigned to User Sorted by Date
	public List<Job> getAssignedJobsSortedByDate(User user) {
	    List<Job> assignedJobs = getAssignedJobs(user);
	    assignedJobs.sort(Comparator.comparing(Job::getDate));
	    return assignedJobs;
	}
	
	//Get Jobs Assigned to User Sorted by Date
	public List<Job> getUnAssignedJobsSortedByDate(User user) {
	    List<Job> unassignedJobs = getUnassignedJobs(user);
	    unassignedJobs.sort(Comparator.comparing(Job::getDate));
	    return unassignedJobs;
	}
}

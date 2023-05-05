package com.rossi21.familyPlanner.Services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.rossi21.familyPlanner.Models.Event;
import com.rossi21.familyPlanner.Models.Job;
import com.rossi21.familyPlanner.Models.LoginUser;
import com.rossi21.familyPlanner.Models.User;
import com.rossi21.familyPlanner.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	// Creates a user
	public User create(User u) {
		
		return userRepo.save(u);
	}
	
	// retrieves a user
	public User getOneById(Long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isEmpty()) {
			return null;
		}
		
		return user.get();
	}
	
	// returns all the user's
    public List<User> allUsers() {
    	
        return userRepo.findAll();
    }
    
    // update the user info
    public User updateUser(User u) {
    	
		return userRepo.save(u);
	}
    
    // delete a user
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	
	public List<User> getAssignedUsers(Job job){
		return userRepo.findAllByJobs(job);
	}
	
	public List<User> getUnassignedUsers(Job job){
		return userRepo.findByJobsNotContains(job);
	}
	
	public List<User> getAssignedUsers(Event event){
		return userRepo.findAllByEvents(event);
	}
	
	public List<User> getUnassignedUsers(Event event){
		return userRepo.findByEventsNotContains(event);
	}
	
    public User register(User newUser, BindingResult result) {
    	
        // Reject if email is taken (present in database)
		Optional<User> user = userRepo.findByEmail(newUser.getEmail());
		if(user.isPresent()) {
    		result.rejectValue("email", "Matches", "That email already exists!");
    	} 
		
        // Reject if password doesn't match confirmation
        if (!newUser.getPassword().equals(newUser.getConfirm())) {
        	result.rejectValue("confirm", "Match", "Passwords must match!");
        }
        
        // Return null if result has errors
        if(result.hasErrors()) {
        	return null;
        }
        
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        
        return userRepo.save(newUser);       
    }
    
    public User login(LoginUser newLoginObject, BindingResult result) {
    	
		// Find user in the DB by email
		Optional<User> possibleUser = userRepo.findByEmail(newLoginObject.getEmail());
		
		// Reject if NOT present
		if (!possibleUser.isPresent()) {
			result.rejectValue("email", "Matches", "User  could not be found!");
			return null;
		}
		
		User user = possibleUser.get();
		
		// Reject if BCrypt password match fails
		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Invalid", "Invalid credentials");
		}
		
		// Return null if result has errors
		if(result.hasErrors()) {
			return null;
		}
		
		// Otherwise, return the user object
		return user;
    }
}

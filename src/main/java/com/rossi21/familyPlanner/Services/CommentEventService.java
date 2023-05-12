package com.rossi21.familyPlanner.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rossi21.familyPlanner.Models.CommentEvent;
import com.rossi21.familyPlanner.Repositories.CommentEventRepository;

@Service
public class CommentEventService {

	@Autowired
	private CommentEventRepository comEventRepo;
	
	// Create
	public CommentEvent createCommentEvent(CommentEvent c) {
		
		return comEventRepo.save(c);
	}
	
	// retrieve
	public CommentEvent getOneById(Long id) {
		Optional<CommentEvent> commentEvent = comEventRepo.findById(id);
		if (commentEvent.isEmpty()) {
			return null;
		}
		
		return commentEvent.get();
	}
	
	// return all 
    public List<CommentEvent> allCommentEvents() {
    	
        return comEventRepo.findAll();
    }
    
    // update 
    public CommentEvent updateCommentEvnet (CommentEvent c) {
    	
		return comEventRepo.save(c);
	}
    
    // delete 
	public void delete (Long id) {
		comEventRepo.deleteById(id);
	}
}

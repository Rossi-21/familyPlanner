package com.rossi21.familyPlanner.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rossi21.familyPlanner.Models.Comment;
import com.rossi21.familyPlanner.Repositories.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository comRepo;
	
	// Create
	public Comment createComment(Comment c) {
		
		return comRepo.save(c);
	}
	
	// retrieve
	public Comment getOneById(Long id) {
		Optional<Comment> comment = comRepo.findById(id);
		if (comment.isEmpty()) {
			return null;
		}
		
		return comment.get();
	}
	
	// return all 
    public List<Comment> allComments() {
    	
        return comRepo.findAll();
    }
    
    // update 
    public Comment updateComment (Comment c) {
    	
		return comRepo.save(c);
	}
    
    // delete 
	public void delete (Long id) {
		comRepo.deleteById(id);
	}
}

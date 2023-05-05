package com.rossi21.familyPlanner.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message="Name is required!")
    private String name;
	
	@NotEmpty(message="Location is required!")
    private String location;
	
	@NotNull(message="Date is required!")
    @Future(message="Date must be in the future!")
    @DateTimeFormat(pattern="MM-dd-yyy")
    private Date date;
	
	@NotEmpty(message="Description is required!")
    @Size(min=3, message="Description must be at least 3 characters")
    private String description;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="MM-dd-yyyy")
    private Date createdAt;
    @DateTimeFormat(pattern="MM-dd-yyyy")
    private Date updatedAt;
    
    //@ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name="user_id")
    //private User user;
    
    //@OneToMany(mappedBy="show", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
   // private List<Rating> ratings;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "events_users", 
        joinColumns = @JoinColumn(name = "event_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
	public Event() {}
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}

package com.chrishues.newsletterbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chrishues.newsletterbackend.exceptions.ResourceNotFoundException;

import com.chrishues.newsletterbackend.models.Subscriber;
import com.chrishues.newsletterbackend.repositories.SubscriberRepository;

@RestController
@CrossOrigin
@RequestMapping ("/api/v1/newsletter/")
public class SubscriberController {
	
	@Autowired
	private SubscriberRepository subscriberRepo;

	@GetMapping("allsubscribers")
	public List<Subscriber> getAllSubscribers() {
		return subscriberRepo.findAll();
	}
	
	@GetMapping("subscriber/{id}")
	public ResponseEntity<Subscriber> geSubscriberById(@PathVariable int id) {
		Subscriber subscriber = subscriberRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Subscriber not found."));
				return ResponseEntity.ok(subscriber);
	}
	
	@PostMapping("addsubscriber")
	public Subscriber newSubscriber(@RequestBody Subscriber subscriber) {
		return subscriberRepo.save(subscriber);
	}
	
	
	
	@PutMapping("subscriber/{id}")
	public ResponseEntity<Subscriber> updateSubscriber(@PathVariable int id, @RequestBody Subscriber newSubscriberInfo) {
		Subscriber foundSubscriber = subscriberRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found."));
		
//		Update info of found subscriber using setters with the new info from req.body using getters.
		foundSubscriber.setFirstname(newSubscriberInfo.getFirstname());
		foundSubscriber.setLastname(newSubscriberInfo.getLastname());
		foundSubscriber.setEmail(newSubscriberInfo.getEmail());
		
		Subscriber updatedSubscriber = subscriberRepo.save(foundSubscriber);
		
		return ResponseEntity.ok(updatedSubscriber);
	}
	
	
	@DeleteMapping("subscriber/{id}")
	public ResponseEntity<String> deleteSubscriber(@PathVariable int id) {
		subscriberRepo.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Subscriber not found."));
		
		String message =  "Student has been deleted.";
		
		subscriberRepo.deleteById(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}

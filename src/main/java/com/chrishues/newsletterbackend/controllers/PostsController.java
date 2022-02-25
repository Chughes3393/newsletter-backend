package com.chrishues.newsletterbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chrishues.newsletterbackend.exceptions.ResourceNotFoundException;
import com.chrishues.newsletterbackend.models.Posts;

import com.chrishues.newsletterbackend.repositories.PostsRepository;


@RestController
@RequestMapping ("/api/v1/newsletter/")
public class PostsController {
	
	@Autowired
	private PostsRepository postsRepo;

	@GetMapping("allposts")
	public List<Posts> getAllPostss() {
		return postsRepo.findAll();
	}
	

	@GetMapping("posts/{id}")
	public ResponseEntity<Posts> gePostsById(@PathVariable int id) {
		Posts posts = postsRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found."));
				return ResponseEntity.ok(posts);
	}
	
	@GetMapping("allposts/{title}")
	public List<Posts> getPostsByTitle(@PathVariable String title) {
		List<Posts> posts = postsRepo.findByTitle(title);
		if(posts.isEmpty()) {
			System.out.println(new ResourceNotFoundException("Post with the name " + title + " not found."));
		}
		return postsRepo.findByTitle(title);
	}
	

	@PostMapping("addpost")
	public Posts newposts(@RequestBody Posts posts) {
		return postsRepo.save(posts);
	}
	
	
	@PutMapping("posts/{id}")
	public ResponseEntity<Posts> updatePosts(@PathVariable int id, @RequestBody Posts newPostInfo) {
		Posts foundPosts = postsRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found."));
		

		foundPosts.setTitle(newPostInfo.getTitle());

		
		Posts updatedPost = postsRepo.save(foundPosts);
		
		return ResponseEntity.ok(updatedPost);
	}
	
	
	@DeleteMapping("posts/{id}")
	public ResponseEntity<String> deleteposts(@PathVariable int id) {
//		Find user we want to delete
		postsRepo.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Subscriber not found."));
		
		String message =  "Post has been deleted.";
		
		postsRepo.deleteById(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}


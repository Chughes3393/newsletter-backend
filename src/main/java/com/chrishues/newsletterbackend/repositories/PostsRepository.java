package com.chrishues.newsletterbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chrishues.newsletterbackend.models.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {

	List<Posts> findByTitle(String title);
	
	

}

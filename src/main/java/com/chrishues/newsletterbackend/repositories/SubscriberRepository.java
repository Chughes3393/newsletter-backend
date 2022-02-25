package com.chrishues.newsletterbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chrishues.newsletterbackend.models.Posts;
import com.chrishues.newsletterbackend.models.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {

	List<Subscriber> findByFirstname(String firstname);
}

package com.feedbackmanagement.feedbackinfo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.feedbackmanagement.feedbackinfo.entity.Responses;

import reactor.core.publisher.Flux;

public interface ResponsesRepository extends ReactiveMongoRepository<Responses, String> {

	Flux<Responses> findByEventId(String eventId);
}

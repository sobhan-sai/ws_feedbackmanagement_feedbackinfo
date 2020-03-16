package com.feedbackmanagement.feedbackinfo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.feedbackmanagement.feedbackinfo.entity.Options;

import reactor.core.publisher.Mono;

public interface OptionRepository extends ReactiveMongoRepository<Options, String> {

	Mono<Options> findByQuestionIdAndOptionId(String questionId, String optionId);
}

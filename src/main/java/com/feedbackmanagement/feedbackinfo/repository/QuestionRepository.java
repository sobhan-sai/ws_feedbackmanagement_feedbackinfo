package com.feedbackmanagement.feedbackinfo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.feedbackmanagement.feedbackinfo.entity.Question;

public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {

}

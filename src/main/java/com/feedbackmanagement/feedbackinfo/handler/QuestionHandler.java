package com.feedbackmanagement.feedbackinfo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.feedbackmanagement.feedbackinfo.entity.Message;
import com.feedbackmanagement.feedbackinfo.entity.Question;
import com.feedbackmanagement.feedbackinfo.repository.QuestionRepository;

import reactor.core.publisher.Mono;

@Component
public class QuestionHandler {

	@Autowired
	QuestionRepository questionRepository;
	
	public Mono<ServerResponse> saveQuestion(ServerRequest sererRequest) {
		Mono<Question> userMono = sererRequest.bodyToMono(Question.class);
		return userMono.flatMap(question->questionRepository.save(question).flatMap(savedUser -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(savedUser))));
	}
	
	public Mono<ServerResponse> editQuestion(ServerRequest serverRequest) {
		String questionId=serverRequest.pathVariable("id");
		Mono<Question> updatedQuestion = serverRequest.bodyToMono(Question.class).log().flatMap((item) -> {

			Mono<Question> userMono = questionRepository.findById(questionId).flatMap(currentItem -> {
				currentItem.setQuestionDescription(item.getQuestionDescription());
				return questionRepository.save(currentItem);

			}).log();
			return userMono;
		}).log();
		return updatedQuestion.flatMap(responseItem->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(responseItem)).switchIfEmpty(ServerResponse.badRequest().body(BodyInserters.fromObject(new Message("Question Doesnot exist")))));
	}
}

package com.feedbackmanagement.feedbackinfo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.feedbackmanagement.feedbackinfo.entity.Message;
import com.feedbackmanagement.feedbackinfo.entity.Responses;
import com.feedbackmanagement.feedbackinfo.repository.ResponsesRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ResponsesHandler {

	@Autowired
	ResponsesRepository responsesRepository;

	public Mono<ServerResponse> saveResponses(ServerRequest sererRequest) {
		Flux<Responses> userFlux = sererRequest.bodyToFlux(Responses.class);
		Flux<Responses> responseFlux = responsesRepository.saveAll(userFlux);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(responseFlux, Responses.class);
	}
	
	public Mono<ServerResponse> getResponsesByEventId(ServerRequest serverRequest){
		String eventId=serverRequest.pathVariable("eventId");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(responsesRepository.findByEventId(eventId).log(),Responses.class).log().switchIfEmpty(ServerResponse.badRequest().body(BodyInserters.fromObject(new Message("Event Doesnot exist"))));
	}
}

package com.feedbackmanagement.feedbackinfo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.feedbackmanagement.feedbackinfo.entity.Message;
import com.feedbackmanagement.feedbackinfo.entity.Options;
import com.feedbackmanagement.feedbackinfo.entity.Question;
import com.feedbackmanagement.feedbackinfo.repository.OptionRepository;

import reactor.core.publisher.Mono;

@Component
public class OptionsHandler {

	@Autowired
	OptionRepository optionsRepository;
	
	public Mono<ServerResponse> saveOptions(ServerRequest sererRequest) {
		Mono<Options> userMono = sererRequest.bodyToMono(Options.class);
		return userMono.flatMap(option->optionsRepository.save(option).flatMap(savedUser -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(savedUser))));
	}
	
	public Mono<ServerResponse> editOptions(ServerRequest serverRequest) {
		String questionId=serverRequest.pathVariable("questionId");
		String optionId=serverRequest.pathVariable("optionId");
		Mono<Options> updatedOption = serverRequest.bodyToMono(Options.class).log().flatMap((item) -> {

			Mono<Options> userMono = optionsRepository.findByQuestionIdAndOptionId(questionId,optionId).flatMap(currentItem -> {
				currentItem.setOptionDescription(item.getOptionDescription());
				return optionsRepository.save(currentItem);

			}).log();
			return userMono;
		}).log();
		return updatedOption.flatMap(responseItem->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(responseItem)).switchIfEmpty(ServerResponse.badRequest().body(BodyInserters.fromObject(new Message("Option Doesnot exist")))));
	}

}

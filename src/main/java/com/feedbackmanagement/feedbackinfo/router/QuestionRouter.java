package com.feedbackmanagement.feedbackinfo.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.feedbackmanagement.feedbackinfo.handler.QuestionHandler;

@Configuration
public class QuestionRouter {

	@Bean
	public RouterFunction<ServerResponse> questionRoute(QuestionHandler questionHandler) {
		return RouterFunctions
				.route(RequestPredicates.POST("/feedback/questions/save")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), questionHandler::saveQuestion)
				.andRoute(RequestPredicates.PUT("/feedback/questions/{questionId}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), questionHandler::editQuestion);
	}

}

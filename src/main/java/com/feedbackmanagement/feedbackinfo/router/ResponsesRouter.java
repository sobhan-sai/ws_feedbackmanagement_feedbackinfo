package com.feedbackmanagement.feedbackinfo.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.feedbackmanagement.feedbackinfo.handler.ResponsesHandler;

@Configuration
public class ResponsesRouter {

	@Bean
	public RouterFunction<ServerResponse> responsesRoute(ResponsesHandler responsesHandler) {
		return RouterFunctions
				.route(RequestPredicates.POST("/feedback/rsponses/save")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), responsesHandler::saveResponses)
				.andRoute(
						RequestPredicates.GET("/feedback/responses/{eventId}")
								.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						responsesHandler::getResponsesByEventId);
	}
}
package com.feedbackmanagement.feedbackinfo.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.feedbackmanagement.feedbackinfo.handler.OptionsHandler;

@Configuration
public class OptionsRouter {

	@Bean
	public RouterFunction<ServerResponse> optionsRoute(OptionsHandler optionsHandler) {
		return RouterFunctions
				.route(RequestPredicates.POST("/feedback/options/save")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), optionsHandler::saveOptions)
				.andRoute(RequestPredicates.PUT("/feedback/options/{questionId}/{optionId}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), optionsHandler::editOptions);
	}

}

package com.rbiedrawa.spring.docker.api;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Configuration
public class ApiRouter {

	@Bean
	RouterFunction<ServerResponse> apiRoute() {
		return RouterFunctions.route(GET("hello"), helloWorldFunction());
	}

	private HandlerFunction<ServerResponse> helloWorldFunction() {
		return request -> ServerResponse.ok().body(Mono.just("Hello World!"), String.class);
	}
}

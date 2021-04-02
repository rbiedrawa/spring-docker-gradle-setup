package com.rbiedrawa.spring.docker.api;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class ApiRouter {

	@Bean
	RouterFunction<ServerResponse> apiRoute() {
		return RouterFunctions.route(GET("hello"), helloWorldFunction());
	}

	private HandlerFunction<ServerResponse> helloWorldFunction() {
		return request -> ServerResponse.ok().body(Mono.create(sink -> {
			log.info("Hello World Requested !!!");
			sink.success("Hello World");
		}), String.class);
	}
}

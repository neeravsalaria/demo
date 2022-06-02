package com.demo.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Autowired
	private TokenRelayGatewayFilterFactory filterFactory;

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route("callme", r -> r.path("/callme/**")
//						.filters(f -> f.filters(filterFactory.apply())
//								.removeRequestHeader("Cookie"))
//						.uri("http://192.168.0.2:8040"))
//				.route("catalog", r -> r.path("/catalog/**")
//						.filters(f -> f.filters(filterFactory.apply())
//								.removeRequestHeader("Cookie"))
//						.uri("lb://catalog-service"))
//				.build();
//	}

	@GetMapping(value = "/token")
	public Mono<String> getHome(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
		return Mono.just(authorizedClient.getAccessToken().getTokenValue());
	}

	@GetMapping("/")
	public Mono<String> index(WebSession session) {
		return Mono.just(session.getId());
	}

}

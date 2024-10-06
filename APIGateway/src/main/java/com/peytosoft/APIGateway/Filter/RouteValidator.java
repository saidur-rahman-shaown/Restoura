package com.peytosoft.APIGateway.Filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
	
	public static final List<String> openApiEndpoints = List.of(
            "/auth/signup",
            "/auth/signin",
            "/eureka",
            "/swagger-ui.html",
            "/v3/api-docs/swagger-config",
            "/v3/api-docs",
            "/customers/v3/api_docs",
            "/order/v3/api_docs",
            "/auth/v3/api_docs",
            "/cuisine/v3/api_docs",
            "/restaurant/v3/api_docs",
            "/vendor/v3/api_docs",
            "/swagger-ui/index.html"







    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}

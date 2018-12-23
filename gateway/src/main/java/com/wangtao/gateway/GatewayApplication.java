package com.wangtao.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

// tag::code[]

//@EnableConfigurationProperties(UriConfiguration.class)
@RestController
@SpringBootApplication
public class GatewayApplication {
    // tag::fallback[]
//    @RequestMapping("/fallback")
//    public Mono<String> fallback() {
//        return Mono.just("fallback");
//    }
    // end::fallback[]


    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes().build();
//    }

//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {//, UriConfiguration uriConfiguration
////        String httpUri = uriConfiguration.getHttpbin();
//        StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
//        config.setParts(1);
//        return builder.routes()
//                .route("host_route",
//                        r -> r.path("/a/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:8081"))
//
//                .route("host_route",
//                        r -> r.path("/b/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:8083"))
////
////                .route(p -> p
////                        .path("/get")
////                        .filters(f -> f.addRequestHeader("Hello", "World"))
////                        .uri("http://httpbin.org:80"))
////
////                .route(p -> p
////                        .host("*.hystrix.com")
////                        .filters(f -> f.hystrix(config -> config.setName("mycmd")))
////                        .uri("http://httpbin.org:80"))
//
//
//                .build();
//    }
}

//@ConfigurationProperties
//class UriConfiguration {
//
//    private String httpbin = "http://httpbin.org:80";
//
//    public String getHttpbin() {
//        return httpbin;
//    }
//
//    public void setHttpbin(String httpbin) {
//        this.httpbin = httpbin;
//    }
//}
// end::uri-configuration[]

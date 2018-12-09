##Spring CLoud Gateway Demo

###配置路由规则
```
builder.routes()
        .route("host_route",
                r -> r.path("/a/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:8081"))

        .route("host_route",
                r -> r.path("/b/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:8083"))
        .build();      
```
* 访问`http://localhost:8080/a/hi`,返回`hi form client1`
* 访问`http://localhost:8080/b/hi`,返回`hi form client2`


#####参考资料
* https://github.com/spring-cloud-samples/spring-cloud-gateway-sample
* https://github.com/spring-cloud/spring-cloud-gateway/tree/master/spring-cloud-gateway-sample
* https://github.com/spring-guides/gs-gateway
* https://www.cnblogs.com/linjunwei2017/p/9238083.html
### SpringSecurity保护资源，访问受限资源会跳至授权服务器登录验证，通过后可以访问受限资源

* SecurityConfig.configure(HttpSecurity http) 代码
```
 http
    .authorizeRequests()
    .antMatchers("/guest/**", "/", "/login**").permitAll()
    .anyRequest().authenticated()
    .and()
    .formLogin().and()
    .httpBasic();
```
访问路径`/guest/**`下的资源无需登录 <br/>
访问其他路径如：`/order/**` ，`/product/**`下的资源需登录，会跳转至授权服务器的登录授权页面，授权后可以访问。

### 使用ResourceServer和SpringSecurity同时保护资源时，无法自动跳转
* 使用的是默认配置：`ResourceServer`的`@Order(3)`;`SpringSecurity`的`@Order(100)` 。

### Security filter chain的比较

* 使用ResourceServer和SpringSecurity同时保护资源时的chain
```
Security filter chain: [
     WebAsyncManagerIntegrationFilter
     SecurityContextPersistenceFilter
     HeaderWriterFilter
     LogoutFilter
     OAuth2AuthenticationProcessingFilter
     RequestCacheAwareFilter
     SecurityContextHolderAwareRequestFilter
     AnonymousAuthenticationFilter
     SessionManagementFilter
     ExceptionTranslationFilter
     FilterSecurityInterceptor
   ]
```
* 仅使用SpringSecurity同时保护资源时的chain
```
Security filter chain: [
     WebAsyncManagerIntegrationFilter
     SecurityContextPersistenceFilter
     HeaderWriterFilter
     CsrfFilter
     LogoutFilter
     OAuth2ClientAuthenticationProcessingFilter
     UsernamePasswordAuthenticationFilter
     DefaultLoginPageGeneratingFilter
     DefaultLogoutPageGeneratingFilter
     BasicAuthenticationFilter
     RequestCacheAwareFilter
     SecurityContextHolderAwareRequestFilter
     AnonymousAuthenticationFilter
     SessionManagementFilter
     ExceptionTranslationFilter
     FilterSecurityInterceptor
   ]
```
### 修改使用ResourceServer和SpringSecurity同时保护资源时的Order
在`SecurityConfig`上`@Order(2)`注解后的
```
Security filter chain: [
  WebAsyncManagerIntegrationFilter
  SecurityContextPersistenceFilter
  HeaderWriterFilter
  CsrfFilter
  LogoutFilter
  OAuth2ClientAuthenticationProcessingFilter
  UsernamePasswordAuthenticationFilter
  DefaultLoginPageGeneratingFilter
  DefaultLogoutPageGeneratingFilter
  BasicAuthenticationFilter
  RequestCacheAwareFilter
  SecurityContextHolderAwareRequestFilter
  AnonymousAuthenticationFilter
  SessionManagementFilter
  ExceptionTranslationFilter
  FilterSecurityInterceptor
]
```
然后通过postman获取token访问受限资源，被security拦截跳转至授权服务器的登录授权页面。无法获取资源。不知道咋搞啊？互相覆盖

### 仅使用ResourceServer保护资源
没有`@EnableWebSecurity(debug = true)`配置，控制台无Security filter chain的信息。
访问[http://localhost:8080/order/1](http://localhost:8080/order/1),返回
```
<oauth>
    <error_description>
        Full authentication is required to access this resource
    </error_description>
    <error>unauthorized</error>
</oauth>
```
获取token后可以正常访问

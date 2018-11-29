#####Spring Security控制资源，访问受限资源会跳至授权服务器登录验证，通过后可以访问受限资源

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


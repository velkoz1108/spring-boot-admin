### 开启分页
```
    /**
    * 分页插件
    */
   @Bean
   public PaginationInterceptor paginationInterceptor() {
       return new PaginationInterceptor();
   }
```
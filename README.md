##在application.properties或application.yml中引用pom.xml的变量不生效的问题
引用方式  `@project.name@` ，在application.yml需要加单引号或双引号， `'@project.name@'`

如果使用main方法启动程序则无法替换变量，显示为 @project.name@

![图片名称](start.png)

可行方法如下：
####  mvn clean install 
 * `cd app-hello`
 * `mvn clean install -Dmaven.test.skip=true`
 * `java -jar app-hello.jar`

####  spring-boot  run 
* 双击spring-boot的maven插件run即可

![图片名称](run.png)



##### 当App注册到服务中心（如Eureka、Consul、Zookeeper等），在App中无需Spring-Boot-Admin的依赖，Spring-Admin-Boot可以通过服务中心自动实现监控

![图片名称](auto.png)


package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;


//扫描dao包，
// 另一种方法是在dao上加上@mapper注解也可以
//限定加载Repository注解的类
@MapperScan(basePackages = "com.example.demo.Dao"
            ,annotationClass = Repository.class)
@SpringBootApplication(scanBasePackages = {"com.example.demo"})
//不使用下面两个包的扫描也可以，只要maven里加入了jpa的依赖springboot也可以知道扫描加入
//jpa接口扫描路径
@EnableJpaRepositories(basePackages = "com.example.demo.Controller.MybatisController")
//Mongodb接口扫描路径，并且配置MongoDB的repository的实现类，加一个后缀"stuff"就行，且后缀可自行更换
@EnableMongoRepositories(basePackages = "com.example.demo.Repository",
                         repositoryImplementationPostfix = "stuff")
//扫描实体类的包
@EntityScan(basePackages = "com.example.demo.POJO")
//使用注解驱动缓存机制
@EnableCaching
public class SpringMvcApplication extends SpringBootServletInitializer {


    //这个是为了初始化springBoot用的，方便在外部的Tomcat中运行
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringMvcApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }
}

package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

//扫描dao包，
// 另一种方法是在dao上加上@mapper注解也可以
//限定加载Repository注解的类
@MapperScan(basePackages = "com.example.demo.Dao"
            ,annotationClass = Repository.class)
@SpringBootApplication(scanBasePackages = {"com.example.demo"})
//不使用下面两个包的扫描也可以，只要maven里加入了jpa的依赖springboot也可以知道扫描加入
//jpa接口扫描路径
@EnableJpaRepositories(basePackages = "com.example.demo.Controller")
//扫描实体类的包
@EntityScan(basePackages = "com.example.demo.POJO")
public class SpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @PostConstruct
    public void ViewTransactionalManager(){
        System.out.println(platformTransactionManager.getClass().getName());
    }

}

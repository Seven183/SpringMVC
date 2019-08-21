package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

/**
 * @author siguiqiang
 * @create 2019/3/15
 */
@Configuration
public class ViewTransactionalManager {

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Bean
    @PostConstruct
    public void ViewTransactionalManager(){
        //打印数据库的连接池类名
        //org.springframework.orm.jpa.JpaTransactionManager
       // System.out.println(platformTransactionManager.getClass().getName());
    }
}

package com.example.demo.jdbc.db;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author siguiqiang
 * @create 2019-2-21
 */
@Component
public class DatasourceShow implements ApplicationContextAware {

    ApplicationContext applicationContext = null;
    //spring容器会自动调用这个方法，注入Spring IOC容器
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println(dataSource.getClass().getName());
    }
}

package com.example.demo.Controller.MybatisController;

import com.example.demo.POJO.MybatisPojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author siguiqiang
 * @create 2019-2-22
 */
@Repository
//jpa默认的访问路径是实体类名小写，这里是http://localhost:8080/courses可以实现对实体类的查询
//jpa默认开启分页功能 http://localhost:8080/courses?page=0&size=3&sort=id,desc
//@RepositoryRestResourceg更改默认的courses路径为kk，
//collectionResourceRel为实体集合的名称
//itemResourceRel为单个实体的名称
//@RepositoryRestResource(path = "kk",collectionResourceRel = "ll",itemResourceRel = "mm")
public interface MyJpaRepository extends JpaRepository<Course, Long> {

    //自定义方法
    //@RestResource(path  = " gread ", rel  = " gread")
    List<Course> findByCourseContains( int gread);
}

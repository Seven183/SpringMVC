package com.example.demo.Controller.MybatisController;

import com.example.demo.POJO.MybatisPojo.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author siguiqiang
 * @create 2019-2-22
 */
@RestController
public class JpaController {

    @Autowired
    public MyJpaRepository myJpaRepository = null;

    @PostMapping("/jpa")
    public List<Course> test(){
        return myJpaRepository.findAll();
    }

    @PostMapping("/jpa1")
    public Course insertCourse(@RequestBody Course course){
        return myJpaRepository.save(course);
    }

    @RequestMapping(value = "/jpa2")
    public List<Course> test2(Integer gread){
        return myJpaRepository.findByCourseContains(gread);
    }
}

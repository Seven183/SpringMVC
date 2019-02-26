package com.example.demo.Controller;

import com.example.demo.POJO.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author siguiqiang
 * @create 2019-2-22
 */
@Controller
public class JpaController {


    @Autowired
    public MyJpaRepository myJpaRepository = null;

    @RequestMapping("/jpa")
    @ResponseBody
    public List<Course> test(){
        return myJpaRepository.findAll();
    }
}

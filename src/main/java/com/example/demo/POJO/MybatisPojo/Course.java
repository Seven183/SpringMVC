package com.example.demo.POJO.MybatisPojo;


import lombok.Data;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author siguiqiang
 * @create 2019-2-21
 */
@Data
@Entity(name = "course")
public class Course {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String course;

    public int gread;

}

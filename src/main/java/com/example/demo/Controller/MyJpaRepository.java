package com.example.demo.Controller;

import com.example.demo.POJO.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author siguiqiang
 * @create 2019-2-22
 */
public interface MyJpaRepository extends JpaRepository<Course, Long> {
}

package com.example.demo.POJO;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author siguiqiang
 * @create 2019-2-21
 */
@Entity(name = "course")
@Table(name = "course")
public class Course {

    @javax.persistence.Id
    public int id;

    public String course;

    public int gread;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getGread() {
        return gread;
    }

    public void setGread(int gread) {
        this.gread = gread;
    }
}

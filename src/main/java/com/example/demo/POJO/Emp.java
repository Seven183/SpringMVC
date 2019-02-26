package com.example.demo.POJO;

import org.apache.ibatis.type.Alias;

/**
 * @author siguiqiang
 * @create 2019-2-25
 */
@Alias("emp")
public class Emp {
    private int id;
    private int age;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

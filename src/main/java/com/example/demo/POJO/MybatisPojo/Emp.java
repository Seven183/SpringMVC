package com.example.demo.POJO.MybatisPojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author siguiqiang
 * @create 2019-2-25
 */
@Alias("emp")
@Data

public class Emp {
    private int id;
    private int age;
    private String name;
}

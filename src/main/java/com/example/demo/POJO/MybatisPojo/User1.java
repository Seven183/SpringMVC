package com.example.demo.POJO.MybatisPojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/*** imports ***/
@Alias("user1")
@Data
public class User1 {

	private Long id;
	private String userName;
	private String note;

}
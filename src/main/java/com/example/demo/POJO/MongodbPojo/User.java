package com.example.demo.POJO.MongodbPojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 标识为MongoDB文档
@Document
@Data
@Alias("mongo_user")

public class User implements Serializable {
	private static final long serialVersionUID = -7895435231819517614L;

	// MongoDB文档编号，主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 在MongoDB中使用user_name保存属性
	@Field("user_name")
	private String userName = null;

	private String note = null;
	Integer skip;
	Integer limit;

	// 角色列表
	private List<Role> roles = null;
}
package com.example.demo.POJO.MongodbPojo;

import java.io.Serializable;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**** imports ****/
@Document
@Data
@Alias("role")
public class Role implements Serializable {
	private static final long serialVersionUID = -6843667995895038741L;
	private Long id;
	@Field("role_name")
	private String roleName = null;
	private String note = null;

}
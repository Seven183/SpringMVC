package com.example.demo.Dao;

import com.example.demo.POJO.Emp;
import org.springframework.stereotype.Repository;

/**
 * @author siguiqiang
 * @create 2019-2-25
 */
@Repository
public interface EmpDao {

    Emp getEmp(long id);
    int insertEmp(Emp emp);
}

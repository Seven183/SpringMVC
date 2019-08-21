package com.example.demo.Dao.MybatisDao;

import com.example.demo.POJO.MybatisPojo.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author siguiqiang
 * @create 2019-2-25
 */
@Repository
public interface EmpDao {

    Emp getEmp(long id);
    int insertEmp(Emp emp);
    List<Emp> findEmp();
    List<Emp> findEmp2(int age,String name);
}

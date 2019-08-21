package com.example.demo.Service.MybatisService;

import com.example.demo.Dao.MybatisDao.EmpDao;
import com.example.demo.POJO.MybatisPojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author siguiqiang
 * @create 2019-2-25
 */
@Service
public class EmpService  {

    @Autowired
    public EmpDao empDao = null;


    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public Emp getEmp(long id){
        return empDao.getEmp(id);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public int insertEmp(Emp emp){
        return empDao.insertEmp(emp);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1,propagation = Propagation.REQUIRED)
    public int insertEmpList(Emp emp) {
            return empDao.insertEmp(emp);
    }

    public List<Emp> findEmp() {
        return empDao.findEmp();
    }
    public List<Emp> findEmp(int age,String name) {
        return empDao.findEmp2(age,name);
    }
}

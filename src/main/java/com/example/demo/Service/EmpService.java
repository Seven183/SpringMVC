package com.example.demo.Service;

import com.example.demo.Dao.EmpDao;
import com.example.demo.POJO.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public int insertEmpList(List<Emp> emp) {
        int count = 0;
        for (Emp emp1 : emp) {
            count += empDao.insertEmp(emp1);
        }
        return count;
    }
}

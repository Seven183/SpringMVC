package com.example.demo.Controller;

import com.example.demo.POJO.Emp;
import com.example.demo.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author siguiqiang
 * @create 2019-2-25
 */
@Controller
public class MybatiesController {

    @Autowired
    public EmpService empService;

    @RequestMapping("/getEmp")
    @ResponseBody

    public Emp getEmp(long id){
        return empService.getEmp(id);
    }

    @RequestMapping("/insertEmp")
    @ResponseBody
    public int insertEmp(Emp emp){
        return empService.insertEmp(emp);
    }

    @RequestMapping("/insertEmpList")
    @ResponseBody
    public Map<String ,Object> insertEmpList(int id1,int age1,String name1,int id2,int age2,String name2){

        Emp emp =new Emp();
        emp.setId(id1);
        emp.setAge(age1);
        emp.setName(name1);
        Emp emp2 =new Emp();
        emp2.setId(id2);
        emp2.setAge(age2);
        emp2.setName(name2);
        List list =new ArrayList();
        list.add(emp);
        list.add(emp2);
        int insert= empService.insertEmpList(list);
        Map map = new HashMap();
        map.put("success",insert>0);
        map.put("emp",list);
        return map;
    }

}

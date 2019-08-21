package com.example.demo.Controller.MybatisController;

import com.alibaba.fastjson.JSON;
import com.example.demo.POJO.MybatisPojo.Emp;
import com.example.demo.Service.MybatisService.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author siguiqiang
 * @create 2019-2-25
 */
@RestController
public class MybatiesController {

    @Autowired
    public EmpService empService;

    @PostMapping("/getEmp")
    public Emp getEmp(@RequestBody Emp emp){
        return empService.getEmp(emp.getId());
    }

    @RequestMapping("/hellosa")
    public String hello(){
        return "jfkjdkaj";
    }

    @PostMapping("/insertEmp")
    public int insertEmp(@RequestBody Emp emp){
        return empService.insertEmp(emp);
    }

    @PostMapping("/insertEmpList")
    public Object insertEmpList(@RequestBody List<Emp> emp){
        System.out.println(JSON.toJSONString(emp));
        for ( Emp  s : emp) {
            List list =new ArrayList();
            list.add(emp);
            int insert= empService.insertEmpList(s);
            Map map = new HashMap();
            map.put("success",insert>0);
            map.put("emp",list);
        }
        return "ok";
    }

}

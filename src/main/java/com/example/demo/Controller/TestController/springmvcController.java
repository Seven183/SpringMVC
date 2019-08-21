package com.example.demo.Controller.TestController;

import com.example.demo.POJO.MybatisPojo.Emp;
import com.example.demo.POJO.ValidatorPojo;
import com.example.demo.Service.MybatisService.EmpService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author siguiqiang
 * @create 2019-1-24
 */
@Controller
public class springmvcController {

    @Autowired
    public EmpService empService;


    @RequestMapping(value = "/index")
    @ResponseBody
    public String hello(){
//        ModelAndView as = new ModelAndView();
//        as.setViewName("index1");
        return "index1";
    }

    @PostMapping("/test")
    @ResponseBody
    public Map<String,Object>test(
            @RequestParam("userName") Integer userName,
            @RequestParam(value = "note" ,required = false) String note
    ){
        Map<String ,Object> map = new HashMap<>();
        map.put("userName",userName);
        map.put("note",note);
        return map;
    }

    @GetMapping("/test1")
    @ResponseBody
    public Map<String,Object>test1(
            @RequestParam("ii") int[] ii,
            @RequestParam(value = "jj" ) String[] jj
    ){
        Map<String ,Object> map = new HashMap<>();
        map.put("ii",ii);
        map.put("jj",jj);
        return map;
    }

    @PostMapping("/test2")
    @ResponseBody
    public Map<String,Object>test2(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date date,
            @NumberFormat(pattern = "#,###.##") Double number
            ){
        Map<String ,Object> map = new HashMap<>();
        map.put("date",date);
        map.put("number",number);
        return map;
    }

    @RequestMapping("/getEmp2")
    public ModelAndView getEmp(long id){
        ModelAndView as = new ModelAndView();
        Emp emp1 = empService.getEmp(id);
        as.setViewName("user/details");
        as.addObject("emp",emp1);
        return as;
    }
    @RequestMapping("/getEmp3")
    public ModelAndView getEmp3(long id){
        ModelAndView as = new ModelAndView();
        Emp emp1 = empService.getEmp(id);
        MappingJackson2JsonView json =  new MappingJackson2JsonView();
        as.setView(json);
        as.addObject("emp",emp1);
        return as;
    }

    @RequestMapping("/table")
    public ModelAndView table() {
        // 访问模型层得到数据
        List<Emp> empList = empService.findEmp();
        // 模型和视图
        ModelAndView mv = new ModelAndView();
        // 定义模型视图
        mv.setViewName("User/table");
        // 加入数据模型
        mv.addObject("empList", empList);
        // 返回模型和视图
        return mv;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Emp> list(@RequestParam(value = "age", required = false) int age,
                           @RequestParam(value = "name", required = false) String name) {
        // 访问模型层得到数据
        List<Emp> userList = empService.findEmp(age,name);
        return userList;
    }

    @GetMapping("/add")
    public String add() {
        return "/user/add";
    }

    // 映射JSP页面
    @GetMapping("/format/form")
    public String showFormat() {
        return "/user/formatter";
    }

    // 获取提交参数
    @PostMapping("/commit")
    @ResponseBody
    public Map<String, Object> format(@DateTimeFormat(iso =DateTimeFormat.ISO.DATE) Date date,
                                      @NumberFormat(pattern = "#,###.##") Double number) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("date", date);
        dataMap.put("number", number);
        return dataMap;
    }

    @GetMapping("/valid/page")
    public String validPage() {
        return "/user/pojo";
    }

    /***
     * 解析验证参数错误
     * @param vp —— 需要验证的POJO，使用注解@Valid 表示验证
     * @param errors  错误信息，它由Spring MVC通过验证POJO后自动填充
     * @return 错误信息Map
     */
    @RequestMapping(value = "/valid/validate")
    @ResponseBody
    public Map<String, Object> validate(
            @Valid @RequestBody ValidatorPojo vp, Errors errors) {
        Map<String, Object> errMap = new HashMap<>();
        // 获取错误列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe : oes) {
            String key = null;
            String msg = null;
            // 字段错误
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                key = fe.getField();// 获取错误验证字段名
            } else {
                // 非字段错误
                key = oe.getObjectName();// 获取验证对象名称
            }
            // 错误信息
            msg = oe.getDefaultMessage();
            errMap.put(key, msg);
        }
        return errMap;
    }

    // 测试Model接口
    @GetMapping("/model")
    public String useModel(Long id, Model model) {
        Emp user = empService.getEmp(id);
        model.addAttribute("emp", user);
        // 这里返回字符串，在Spring MVC中，会自动创建ModelAndView且绑定名称
        return "user/user";
    }

    // 测试modelMap类
    @GetMapping("/modelMap")
    public ModelAndView useModelMap(Long id, ModelMap modelMap) {
        Emp user = empService.getEmp(id);
        ModelAndView mv = new ModelAndView();
        // 设置视图名称
        mv.setViewName("user/user");
        // 设置数据模型，此处modelMap并没有和mv绑定，这步系统会自动处理
        modelMap.put("emp", user);
        return mv;
    }

    // 测试ModelAndView
    @GetMapping("/mav")
    public ModelAndView useModelAndView(Long id, ModelAndView mv) {
        Emp user = empService.getEmp(id);
        // 设置数据模型
        mv.addObject("emp", user);
        // 设置视图名称
        mv.setViewName("user/user");
        return mv;
    }
}


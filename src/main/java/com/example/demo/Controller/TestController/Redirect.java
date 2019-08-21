package com.example.demo.Controller.TestController;

import com.example.demo.POJO.MybatisPojo.Emp;
import com.example.demo.Service.MybatisService.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author siguiqiang
 * @create 2019/5/23
 */
@Controller
public class Redirect {

    @Autowired
    public EmpService empService;

    	// 显示用户
	@GetMapping("/show")
	public String showUser(Long id, Model model) {
	    Emp user = empService.getEmp(id);
	    model.addAttribute("user", user);
	    return "user/user";
	}

	// 使用字符串指定跳转
	@GetMapping("/redirect1")
	public String redirect1(int age, String name) {
	    Emp user = new Emp();
        user.setName(name);
        user.setAge(age);
	    // 插入数据库后，回填user的id
	    empService.insertEmp(user);
	    return "redirect:/show?id=" + user.getId();
	}

	// 使用模型和视图指定跳转
	@GetMapping("/redirect2")
	public ModelAndView redirect2(int age, String name) {
	    Emp user = new Emp();
        user.setName(name);
        user.setAge(age);
	    empService.insertEmp(user);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("redirect:/show?id=" + user.getId());
	    return mv;
	}

//    // 显示用户
//    // 参数user直接从数据模型RedirectAttributes对象中取出
//    @RequestMapping("/showUser")
//    public String showUser(Emp emp) {
//        System.out.println(emp.getId());
//        return "user/user";
//    }
//
//    // 使用字符串指定跳转
//    @RequestMapping("/redirect1")
//    public String redirect1(int age, String name, RedirectAttributes ra) {
//        Emp user = new Emp();
//        user.setName(name);
//        user.setAge(age);
//        empService.insertEmp(user);
//        // 保存需要传递给重定向的对象
//        ra.addFlashAttribute("emp", user);
//        return "redirect:/showUser";
//    }
//
//    // 使用模型和视图指定跳转
//    @RequestMapping("/redirect2")
//    public ModelAndView redirect2(int age, String name,
//                                  RedirectAttributes ra) {
//        Emp user = new Emp();
//        user.setName(name);
//        user.setAge(age);
//        empService.insertEmp(user);
//        // 保存需要传递给重定向的对象
//        ra.addFlashAttribute("emp", user);
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("redirect:/showUser");
//        return mv;
//    }
//
//    @GetMapping("/header/page")
//    public String headerPage() {
//        return "view/header";
//    }
//
//    @PostMapping("/header/user")
//    @ResponseBody
//    // 通过@RequestHeader接收请求头参数
//    public Emp headerUser(@RequestHeader("id") Long id) {
//        Emp user = empService.getEmp(id);
//        return user;
//    }
}

package com.example.demo.Controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author siguiqiang
 * @create 2019-1-24
 */
@Controller
public class springmvcController {

    @RequestMapping(value = "/index")
    public String hello(){
//        ModelAndView as = new ModelAndView();
//        as.setViewName("index1");
        return "formatter";
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

}


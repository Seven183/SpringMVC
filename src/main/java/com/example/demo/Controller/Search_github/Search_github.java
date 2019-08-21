package com.example.demo.Controller.Search_github;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author siguiqiang
 * @create 2019/7/23
 */
@RestController
public class Search_github {

    @RequestMapping("/Search/{searchName}")
    public String Search(@PathVariable String searchName){
        String url = "https://api.github.com/search/users?q="+searchName;
        System.out.println("jdkals");
        return  url;

    }
}

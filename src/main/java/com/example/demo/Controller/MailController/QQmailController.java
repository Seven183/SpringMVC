package com.example.demo.Controller.MailController;

import com.example.demo.Service.MailService.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.io.File;

/**
 * @author siguiqiang
 * @create 2019/5/23
 */
@Controller
@Configuration
public class QQmailController {

    @Autowired
    MailService mailService;

    public void sendMail(){
        mailService.SendMail(
                "1830883597@qq.com",
                "18325649801@163.com",
                "1830883597@qq.com",
                "测试",
                "你好djksjdk");
    }


    public void sendAttachMail(){
        mailService.SendAttachMail(
                "1830883597@qq.com",
                "18325649801@163.com",
                "测试",
                "你好djksjdk",
                new File("C:\\Users\\司桂强\\Desktop\\司桂强毕设\\070815008_司桂强_组合.pdf"));
    }
}

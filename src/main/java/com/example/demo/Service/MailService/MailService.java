package com.example.demo.Service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author siguiqiang
 * @create 2019/3/22
 */
@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender ;

    public void SendMail(String from,String to,String cc,String sbject,String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject(sbject);
        simpleMailMessage.setText(content);

        javaMailSender.send(simpleMailMessage);
    }

    public void SendAttachMail(String from, String to,String sbject, String content, File file){
        try{

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(sbject);
            mimeMessageHelper.setText(content);
            mimeMessageHelper.addAttachment(file.getName(),file);
            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

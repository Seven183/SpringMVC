package com.example.demo.Controller.UpLoadController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author siguiqiang
 * @create 2019/3/21
 */
@RestController
public class UpLoadController {

   SimpleDateFormat sfg = new SimpleDateFormat("yyyy/MM/dd/");

    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile , HttpServletRequest req){

        //上传文件的保存路径为项目运行目录下的uploadFile文件夹，并用日期对其归类
        String realpath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        String date = sfg.format(new Date());
        File folder = new File(realpath + date);
        if(!folder.isDirectory()){
            folder.mkdirs();
        }

        //给文件夹重命名
        String oldname = uploadFile.getOriginalFilename();
        String newname = UUID.randomUUID().toString() + oldname.substring(oldname.lastIndexOf("."),oldname.length());
        try{
            //保存文件
            uploadFile.transferTo(new File(folder,newname));

            //生成上传的访问路径，并将访问路径返回
            String filePath = req.getScheme() + "://" + req.getServerName() + ":"
                    + req.getServerPort()+ "/uploadFile" + date + newname;

            return filePath;

        }catch (Exception e){
            e.printStackTrace();
        }
       return "上传失败";
    }


    @PostMapping("/uploads")
    public String uploads(MultipartFile[] uploadFiles , HttpServletRequest req){

            //上传文件的保存路径为项目运行目录下的uploadFile文件夹，并用日期对其归类
            String realpath = req.getSession().getServletContext().getRealPath("/uploadFiles/");
            String date = sfg.format(new Date());
            File folder = new File(realpath + date);
            if(!folder.isDirectory()){
                folder.mkdirs();
            }

        for (MultipartFile multipartFile : uploadFiles) {
            //给文件夹重命名
            String oldname = multipartFile.getOriginalFilename();
            String newname = UUID.randomUUID().toString() + oldname.substring(oldname.lastIndexOf("."),oldname.length());
            try{
                //保存文件
                multipartFile.transferTo(new File(folder,newname));

                //生成上传的访问路径，并将访问路径返回
                String filePaths = req.getScheme() + "://" + req.getServerName() + ":"
                        + req.getServerPort()+ "/uploadFiles" + date + newname;
                return filePaths;

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return "上传失败";
    }
}

package com.example.demo.Controller.TestController;

import com.example.demo.Service.MybatisService.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author siguiqiang
 * @create 2019/5/23
 */
@Controller
public class MvcController {

    @Autowired
    public EmpService empService;

    /**
     * 打开文件上传请求页面
     * @return 指向JSP的字符串
     */
    @GetMapping("/upload/page")
    public String uploadPage() {
        return "/view/upload";
    }

    // 使用HttpServletRequest作为参数
    @PostMapping("/upload/request")
    @ResponseBody
    public Map<String, Object> uploadRequest(HttpServletRequest request) {
        boolean flag = false;
        MultipartHttpServletRequest mreq = null;
        // 强制转换为MultipartHttpServletRequest接口对象
        if (request instanceof MultipartHttpServletRequest) {
            mreq = (MultipartHttpServletRequest) request;
        } else {
            return dealResultMap(false, "上传失败");
        }
        // 获取MultipartFile文件信息
        MultipartFile mf = mreq.getFile("file");
        // 获取源文件名称
        String fileName = mf.getOriginalFilename();
        File file = new File(fileName);
        try {
            // 保存文件
            mf.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }

    // 使用Spring MVC的MultipartFile类作为参数
    @PostMapping("/upload/multipart")
    @ResponseBody
    public Map<String, Object> uploadMultipartFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }


    // 使用Servlet的API的part类作为参数，推荐使用这个
    @PostMapping("/upload/part")
    @ResponseBody
    public Map<String, Object> uploadPart(Part file) {
        // 获取提交文件名称
        String fileName = file.getSubmittedFileName();
        try {
            // 写入文件
            file.write(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }

    //多文件上传
    // 使用Servlet的API的part类作为参数，推荐使用这个
    @PostMapping("/upload/parts")
    @ResponseBody
    public Map<String, Object> uploadParts(Part[] file) {
        for (Part part : file) {
            String fileName = part.getSubmittedFileName();
            try {
                // 写入文件
                part.write(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return dealResultMap(false, "上传失败");
            }
            // 获取提交文件名称
        }
        return dealResultMap(true, "上传成功");
    }

    // 处理上传文件结果
    private Map<String, Object> dealResultMap(boolean success, String msg) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }


    @GetMapping("/interceptor/start")
    public String start() {
        System.out.println("执行处理器逻辑");
        return "view/welcome";
    }
}

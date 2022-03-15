package com.zc.controller;

import com.zc.service.FileUploadService;
import com.zc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class FileUploadController extends BaseController {
    @Autowired
    FileUploadService fileUploadService;
    //使用默认路径
    @RequestMapping("/upload")
    public JsonResult upload(MultipartFile file) throws Exception {
        fileUploadService.upload(file, null);
        return new JsonResult(OK);
    }

    //自定义路径
    @RequestMapping("/upload/template")
    public JsonResult uploadPlace(MultipartFile file) throws Exception {
        fileUploadService.upload(file, "H:\\upload");
        return new JsonResult(OK);
    }

    //下载
    @GetMapping("/download/file")
    public JsonResult downloadFile(HttpServletResponse response) throws IOException {
        fileUploadService.download(response, "上传模板");
        return new JsonResult(OK);
    }
}

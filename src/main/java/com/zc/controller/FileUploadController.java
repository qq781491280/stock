package com.zc.controller;

import com.zc.service.FileService;
import com.zc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController extends BaseController {

    @Autowired
    FileService fileService;

    @RequestMapping("/upload")
    @ResponseBody
    public JsonResult upload(@RequestParam("file") MultipartFile file){
        fileService.upload(file);
        return new JsonResult(OK);
    }
}

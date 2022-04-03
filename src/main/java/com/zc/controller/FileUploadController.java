package com.zc.controller;

import com.zc.domian.Files;
import com.zc.domian.Goods;
import com.zc.service.FileService;
import com.zc.service.UserService;
import com.zc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileUploadController extends BaseController {

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

    @RequestMapping("/upload")
    @ResponseBody
    public JsonResult upload(@RequestParam("file") MultipartFile file){
        fileService.upload(file);
        return new JsonResult(OK);
    }

    @RequestMapping("/uploadavatar")
    @ResponseBody
    public JsonResult uploadavatar(@RequestParam("file") MultipartFile file,@RequestParam("username") String username){
        Map<String, String> map = fileService.uploadAvatar(file, username);
        return new JsonResult(OK,map);
    }

    @RequestMapping("/uploadlist")
    @ResponseBody
    public  JsonResult uploadlist(@RequestParam("file") MultipartFile[] file,String mname){

        List<Files> uploadlist = fileService.uploadlist(file,mname);

        return new JsonResult(OK,uploadlist);
    }

    @PostMapping
    public  JsonResult delimg(@RequestBody Files files){
        fileService.delimg(files);
        return new JsonResult(OK);
    }
}

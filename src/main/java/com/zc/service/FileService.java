package com.zc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domian.Files;
import com.zc.domian.Goods;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Map;

public interface FileService extends IService<Files> {
    void upload(MultipartFile file);

    Map<String, String> uploadAvatar(MultipartFile file,String username);

    List<Files> uploadlist(MultipartFile[] files,String mname);

    void  delimg(Files files);
}
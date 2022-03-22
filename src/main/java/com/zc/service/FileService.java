package com.zc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.domian.Files;
import org.springframework.web.multipart.MultipartFile;

public interface FileService extends IService<Files> {
    void upload(MultipartFile file);

    void uploadAvatar(MultipartFile file,String username);
}

package com.zc.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zc.domian.Upload;
import com.zc.mapper.UploadMapper;
import com.zc.service.FileUploadService;
import com.zc.utils.File.FileUploadUtils;
import com.zc.utils.File.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;


@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    UploadMapper uploadMapper;

    @Override
    public void upload(MultipartFile file, String baseDir) throws Exception {
        //就算什么也不传，controller层的file也不为空，但是originalFilename会为空（亲测）
        String originalFilename = file.getOriginalFilename();

        if(originalFilename == null || "".equals(originalFilename)) {
            throw new FileNotFoundException("上传文件不能为空");
    }
        //检测是否上传过同样的文件，如果有的话就删除。（这边可根据个人的情况修改逻辑）
        QueryWrapper<Upload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("old_name", originalFilename);
        Upload oldEntity = uploadMapper.selectOne(queryWrapper);

        //新的文件
        Upload upload = new Upload();
        upload.setCreateTime(new Date());
        upload.setUpdateTime(new Date());
        upload.setOldName(file.getOriginalFilename());        //这边可以根据业务修改，项目中不要写死
        upload.setName("上传模板");
        String fileLocation = null ;
        if(baseDir != null) {
            fileLocation = FileUploadUtils.upload(baseDir, file);
        }else {
            fileLocation = FileUploadUtils.upload(file);
        }

        upload.setLocation(fileLocation);
        uploadMapper.insert(upload);

        if(oldEntity != null) {
            //确保新的文件保存成功后，删除原有的同名文件(实体文件 and 数据库文件)
            FileUtils.deleteFile(oldEntity.getLocation());
            uploadMapper.deleteById(oldEntity.getId());
        }
    }

    @Override
    public void download(HttpServletResponse response, String newName) throws IOException {
        QueryWrapper<Upload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", newName);
        Upload upload = uploadMapper.selectOne(queryWrapper);

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");        //这边可以设置文件下载时的名字，我这边用的是文件原本的名字，可以根据实际场景设置
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(upload.getOldName(), "UTF-8"));
        FileUtils.writeBytes(upload.getLocation(), response.getOutputStream());
    }
}

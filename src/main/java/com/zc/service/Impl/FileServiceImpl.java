package com.zc.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.domian.Files;
import com.zc.domian.Goods;
import com.zc.domian.User;
import com.zc.mapper.FileMapper;
import com.zc.mapper.GoodsMapper;
import com.zc.mapper.UserMapper;
import com.zc.service.FileService;
import com.zc.utils.FileUtils;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, Files> implements FileService {
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${server.port}")
    private int POST;
    @Value("${file.avatarFolder}")
    private String avatarFolder;
    @Autowired
    FileMapper fileMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @SneakyThrows
    @Override
    public void upload(MultipartFile file) {
        String originalFilename =  file.getOriginalFilename();
        Files files = new Files();
        if(originalFilename == null || "".equals(originalFilename)) {
            throw new FileNotFoundException("上传文件不能为空");
        }
        //检测是否上传过同样的文件，如果有的话就删除。（这边可根据个人的情况修改逻辑）
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("old_name", originalFilename);
        Files oldEntity = fileMapper.selectOne(queryWrapper);
        //使用UUID生成唯一标识
        String randomNumber = UUID.randomUUID().toString().replace("-", "");
        //创建一个唯一标识作为评论的主键以及队伍的一个字段，方便用来保存数据将队伍与队伍描述建立起关系
        //获取文件的原始名
        String oldFilename = file.getOriginalFilename();
        //使用FilenameUtils获得文件的后缀(先导依赖Commons-fileUpload)
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        //生成新的文件名(队伍名+随机数+后缀名)
        String newFileName = randomNumber + "." + extension;
        //使用ResourceUtils类路径再获取文件保存的路径
        java.io.File dateDir = new java.io.File(uploadFolder);
        if (!dateDir.exists()) {
            //判断目录是否存在，不存在则直接创建
            dateDir.mkdirs();
        }
        try {
            file.transferTo(new java.io.File(uploadFolder, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //上传完毕，存入数据库中的地址为：
        String invented_address="http://127.0.0.1:" + POST + "/api/file/" + newFileName;
        String location = uploadFolder+newFileName;
        files.setLocation(invented_address);
        files.setCreateTime(new Date());
        files.setUpdateTime(new Date());
        files.setOldName(oldFilename);
        files.setDescription(location);
        files.setUid("admin");
        files.setName("上传模板");
        fileMapper.insert(files);
        if(oldEntity != null) {
            //确保新的文件保存成功后，删除原有的同名文件(实体文件 and 数据库文件)
            FileUtils.deleteFile(oldEntity.getDescription());
            fileMapper.deleteById(oldEntity.getId());
        }
    }

    @SneakyThrows
    @Override
    public void uploadAvatar(MultipartFile file, String username) {
        User user = userMapper.selectByUsername(username);
        String originalFilename =  file.getOriginalFilename();
        if(originalFilename == null || "".equals(originalFilename)) {
            throw new FileNotFoundException("上传文件不能为空");
        }
        //使用UUID生成唯一标识
        String randomNumber = UUID.randomUUID().toString().replace("-", "");
        //创建一个唯一标识作为评论的主键以及队伍的一个字段，方便用来保存数据将队伍与队伍描述建立起关系
        //使用FilenameUtils获得文件的后缀(先导依赖Commons-fileUpload)
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        //生成新的文件名(队伍名+随机数+后缀名)
        String newFileName = randomNumber + "." + extension;
        //使用ResourceUtils类路径再获取文件保存的路径
        java.io.File dateDir = new java.io.File(avatarFolder);
        if (!dateDir.exists()) {
            //判断目录是否存在，不存在则直接创建
            dateDir.mkdirs();
        }
        try {
            file.transferTo(new java.io.File(avatarFolder, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String userimg="http://127.0.0.1:" + POST + "/api/file/" + newFileName;
        //获取路径后面的文件名
        String s = user.getUserimg();
        String trim = s.trim();
        String fileName = trim.substring(trim.lastIndexOf("/")+1);
        String location = avatarFolder+fileName;
        if (!user.getUserimg().equals("http://127.0.0.1:80/api/file/toux.png")){
            FileUtils.deleteFile(location);
        }

        userMapper.updateAvatarByUsername(username,userimg);
    }

    @SneakyThrows
    @Override
    public List<Files> uploadlist(MultipartFile[] files,String mname){
        List <Files> filesList = new ArrayList<>();
        if (files.length>0){
            for (MultipartFile file: files){
                String originalFilename =  file.getOriginalFilename();
                Files filelist = new Files();
                //检测是否上传过同样的文件，如果有的话就删除。（这边可根据个人的情况修改逻辑）
                QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("old_name", originalFilename);
                Files oldEntity = fileMapper.selectOne(queryWrapper);
                //获取文件的原始名
                String oldFilename = file.getOriginalFilename();
                //使用UUID生成唯一标识
                String randomNumber = UUID.randomUUID().toString().replace("-", "");
                //创建一个唯一标识作为评论的主键以及队伍的一个字段，方便用来保存数据将队伍与队伍描述建立起关系
                //使用FilenameUtils获得文件的后缀(先导依赖Commons-fileUpload)
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                //生成新的文件名(队伍名+随机数+后缀名)
                String newFileName = randomNumber + "." + extension;
                //使用ResourceUtils类路径再获取文件保存的路径
                java.io.File dateDir = new java.io.File(uploadFolder);
                if (!dateDir.exists()) {
                    //判断目录是否存在，不存在则直接创建
                    dateDir.mkdirs();
                }try {
                    file.transferTo(new java.io.File(uploadFolder, newFileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //上传完毕，存入数据库中的地址为：
                String invented_address="http://127.0.0.1:" + POST + "/api/file/" + newFileName;
                String location = uploadFolder+newFileName;
                filelist.setLocation(invented_address);
                filelist.setCreateTime(new Date());
                filelist.setUpdateTime(new Date());
                filelist.setOldName(oldFilename);
                filelist.setDescription(location);
                filelist.setUid("admin");
                filelist.setName(mname);
                fileMapper.insertImg(filelist);
                if(oldEntity != null) {
                    //确保新的文件保存成功后，删除原有的同名文件(实体文件 and 数据库文件)
                    FileUtils.deleteFile(oldEntity.getDescription());
                    fileMapper.deleteById(oldEntity.getId());
                }
//                Long fileId = filelist.getId();
//                fileMapper.addGoodsImg(id,fileId);
                filesList.add(filelist);
            }
            return filesList;
        }

        return  filesList;
    }

    @Override
    public void delimg(Files files) {
            //确保新的文件保存成功后，删除原有的同名文件(实体文件 and 数据库文件)
            FileUtils.deleteFile(files.getDescription());
            fileMapper.deleteByimgid(files.getId());
            fileMapper.deleteByurl(files.getDescription());
    }

}

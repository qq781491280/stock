package com.zc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.domian.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> selectUserList();
    User selectByUsername(@Param("username") String username);
//    Boolean selectByUsernameAndPassword(@Param("username")String username, @Param("password") String password);
    Integer updateByUsername(User user);
    Integer updateAvatarByUsername(@Param("username") String username,@Param("userimg") String userimg);
    Integer updatePasswordByUsername(@Param("username")String username,@Param("newpassword")String newpassword,@Param("oldpassword")String oldpassword);
    Integer updatetoken(@Param("username") String username,@Param("token") String token);
    Integer register(User user);
}

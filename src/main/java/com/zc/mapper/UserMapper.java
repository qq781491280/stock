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
    Boolean selectByUsernameAndPassword(@Param("username")String username, @Param("password") String password);
    Boolean updateByUsername(User user);
}

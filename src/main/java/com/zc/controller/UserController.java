package com.zc.controller;

import com.zc.domian.User;
import com.zc.service.UserService;
import com.zc.utils.R;
import com.zc.utils.Rs;
import com.zc.utils.judge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;


  /**
   * 查询全部
   * @return
   */
    @GetMapping
    public R getAll(){
      List<User> list = userService.UserList();
      R r = new R();
      if (!list.isEmpty()){
        r = new R(true, list);
      }else {
         r = new R(false, list);
      }
      return r;
    }

  /**
   * 按帐号查询用户信息
   * @param username 帐号
   * @return
   */
  @GetMapping("{username}")
  public R getByUsername(@PathVariable String username){
      User byUsername = userService.getByUsername(username);
      R r = new R();
      if (byUsername!=null){
        r = new R(true, byUsername);
      }else {
        r = new R(false, byUsername,"找不到此帐号");
      }
      return r;
    }

  /**
   * 登陆
   * @param username 帐号
   * @param password 密码
   * @return
   */
    @GetMapping("{username}/{password}")
  public Rs login(@PathVariable String username, @PathVariable String password){
      Boolean flag = userService.getByUsernameAndPassword(username, password);
      return new Rs(flag,flag?"登录成功":"登录失败");
    }

  @PostMapping
  public Rs register(@RequestBody User user){
      Boolean existsUsername = userService.existsUsername(user.getUsername());
      if (judge.Judge(user.getUsername(),user.getPassword())){
          if (existsUsername){
          }else {
              user.setRoleId(2);
              userService.save(user);
          }
      }else {
          return new Rs(false,"帐号或者密码不合法");
      }

      return new Rs(!existsUsername,!existsUsername?"注册成功":"帐号已被注册");

  }

    @PutMapping
    public Rs updateuserinfo(@RequestBody User user){
            Boolean flag = userService.modfiy(user);
            return new Rs(flag,flag?"修改成功":"修改失败");
    }

    @RequestMapping("/modfiy")
    @ResponseBody
    public Rs updatePasswordAndUsername(@RequestParam("username") String username,@RequestParam("newpassword") String newpassword,@RequestParam("oldpassword") String oldpassword){


        return null;
    }


}

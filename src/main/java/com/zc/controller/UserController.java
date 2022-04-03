package com.zc.controller;
import com.zc.domian.User;
import com.zc.service.UserService;
import com.zc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

  @Autowired
  private UserService userService;


  /**
   * 查询全部
   * @return
   */
    @GetMapping
    public JsonResult getAll(){
      List<User> list = userService.UserList();
      return new JsonResult(OK,list);
    }
  /**
   * 按帐号查询用户信息
   * @param username 帐号
   * @return
   */
  @GetMapping("{username}")
  public JsonResult getByUsername(@PathVariable String username){
      User byUsername = userService.getByUsername(username);
      return new JsonResult(OK,byUsername);
    }

  /**
   * 登陆
   * @param username 帐号
   * @param password 密码
   * @return
   */
    @GetMapping("{username}/{password}")
  public JsonResult login(@PathVariable String username, @PathVariable String password){
        User data = userService.login(username, password);
        return new JsonResult(OK,data);
    }

    /**
     * 注册
      * @param user
     * @return
     */
  @PostMapping
  public JsonResult register(@RequestBody User user) {
      userService.register(user);
      Map<String, String> map = new HashMap<>();
      map.put("token",user.getToken());
      return new JsonResult(OK,map);

  }
    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping
    public JsonResult updateuserinfo(@RequestBody User user){
            userService.modfiy(user);
            return new JsonResult(OK);
    }

    /**
     * 修改密码
     * @param username
     * @param newpassword
     * @param oldpassword
     * @return
     */
    @RequestMapping("/modfiypassword")
    @ResponseBody
    public JsonResult updatePasswordAndUsername(@RequestParam("username") String username,@RequestParam("newpassword") String newpassword,@RequestParam("oldpassword") String oldpassword){
        userService.modfiypassword(username,newpassword,oldpassword);
        return new JsonResult(OK);
    }
}

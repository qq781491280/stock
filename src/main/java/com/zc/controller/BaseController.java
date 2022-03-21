package com.zc.controller;

import com.zc.service.ex.*;
import com.zc.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
public class BaseController {
    public static  final  int OK = 200;
    /*@ExceptionHandler用于同一处理方法抛出的异常*/
@ExceptionHandler(ServiceException.class)
    public JsonResult handleException(Throwable e){
    JsonResult result = new JsonResult(e);
    if (e instanceof UsernameDuplicateException) result.setState(400);
    else if (e instanceof InsertException) result.setState(500);
    else if (e instanceof UserNotFoundException) result.setState(401);
    else if (e instanceof PasswordNotMatchException) result.setState(402);
    else if (e instanceof VerifyUsernameException) result.setState(403);
    else if (e instanceof VerifyPasswordException) result.setState(403);
    else if (e instanceof FileNotEmptyException) result.setState(501);
    else if (e instanceof GoodsNotEmptyException) result.setState(601);
    else if (e instanceof GoodsException) result.setState(602);
    return result;
}
}

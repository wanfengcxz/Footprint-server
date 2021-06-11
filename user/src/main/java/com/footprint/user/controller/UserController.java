package com.footprint.user.controller;

import com.footprint.eureka.entity.User;
import com.footprint.eureka.utils.AESUtil;
import com.footprint.eureka.utils.Result;
import com.footprint.eureka.utils.ResultUtil;
import com.footprint.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/json")
public class UserController {

    @Autowired
    private UserService userService = null;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Result register(@RequestParam("user_name") String userName,
                           @RequestParam("phone") String phone,
                           @RequestParam("password") String password,
                           @RequestParam("face_url") String faceUrl,
                           @RequestParam("gender") String gender,
                           @RequestParam("city") String city,
                           @RequestParam("province") String province) {
        // 检测手机号的合法性
        if (this.userService.checkPhone(phone)) {
            User user = new User(0, userName, phone, password, faceUrl, province, city, gender);
            // 用户进行注册
            if (this.userService.registerUser(user) == 1)
                return ResultUtil.success();
            else
                return ResultUtil.unknownError();

        } else
            return ResultUtil.phoneRepeatition();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Result login(@RequestParam("phone") String phone,
                        @RequestParam("password") String password) {
        // 当前手机号不可用说明已经注册过了
        if (!this.userService.checkPhone(phone)){
            User findCondition = new User(-1,null,phone,password,null, null,null,null);
            User res = this.userService.isUserExist(findCondition);
            if (res == null)
                return ResultUtil.passwordError();
            else{
                res.setPassword(AESUtil.encode(String.valueOf(res.getUserId())));
                return ResultUtil.success(res);
            }
        }
        // 没有注册过
        else
            return ResultUtil.userNotExist();
    }
}



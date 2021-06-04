package com.footprint.user.service;

import com.footprint.eureka.entity.User;

public interface UserService {

    /**
     * 检查手机号是否可用
     * @param phone 手机号
     * @return 是否可用
     */
    public boolean checkPhone(String phone);

    /**
     * 注册用户
     * @param user 用户Bean
     */
    public int registerUser(User user);

    /**
     * 根据条件判断用户是否存在
     * @param user 用户Bean
     * @return 如果存在返回用户信息
     */
    public User isUserExist(User user);



}

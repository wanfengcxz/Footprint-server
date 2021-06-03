package com.footprint.user.service;

import com.footprint.eureka.entity.User;
import com.footprint.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao = null;

    /**
     * 检查手机号是否可用
     * @param phone 手机号
     * @return 是否可用
     */
    @Override
    public boolean checkPhone(String phone){
        return this.userDao.selectCount(new User(-1, null, phone, null, null, null,null,null)) == 0;
    }

    /**
     * 注册用户
     * @param user 用户Bean
     */
    @Override
    public int registerUser(User user){
        return this.userDao.insertUser(user);
    }

    /**
     * 根据条件判断用户是否存在
     * @param user 用户Bean
     * @return 如果存在返回用户信息
     */
    @Override
    public User isUserExist(User user){
        return this.userDao.selectUser(user);
    }

}

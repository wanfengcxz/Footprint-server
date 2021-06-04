package com.footprint.user.dao;

import com.footprint.eureka.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    /**
     * 新增用户
     * @param user 用户Bean
     * @return 影响行数
     */
    public int insertUser(User user);

    /**
     * 根据条件查找用户
     * @param user
     * @return 查找到的用户列表
     */
    public List<User> selectUsers(User user);

    /**
     * 根据条件查询单个用户
     * @param user 用户Bean
     * @return 查找到的用户
     */
    public User selectUser(User user);

    /**
     * 统计指定条件用户的数量
     * @param user 用户 Bean
     * @return 数量
     */
    public int selectCount(User user);

}

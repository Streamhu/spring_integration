package com.hh.mybatis.dao;

import com.hh.mybatis.User;

import java.util.List;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/10 11:10
 */
public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll();
}

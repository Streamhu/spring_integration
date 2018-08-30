package com.hh.mybatis;

import com.hh.mybatis.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/10 11:16
 */
@Service(value = "mybatisServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> select() {
        return userDao.selectAll();
    }
}

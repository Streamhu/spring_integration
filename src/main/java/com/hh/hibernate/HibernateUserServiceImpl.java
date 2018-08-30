package com.hh.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/27 17:41
 */
@Service
public class HibernateUserServiceImpl implements HibernateUserService{

    @Autowired
    private HibernateUserDao hibernateUserDao;

    @Override
    public List<User> select() {
        return hibernateUserDao.getUserList();
    }
}

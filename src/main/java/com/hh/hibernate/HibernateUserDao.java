package com.hh.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/27 17:42
 */
@Repository
public class HibernateUserDao {

    @Resource
    SessionFactory sessionFactory;

    public List<User> getUserList(){
        Session session = sessionFactory.openSession();
        String hsql = "from User";
        Query query = session.createQuery(hsql);
        List<User> userList= query.list();
        return userList;
    }
}

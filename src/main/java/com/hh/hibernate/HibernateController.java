package com.hh.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/27 17:29
 */
@Controller
@RequestMapping(value="hibernate")
public class HibernateController {

    @Autowired
    private HibernateUserService hibernateUserService;

    @RequestMapping(value="test")
    public String test(){
        List<User> userList = hibernateUserService.select();
        for(User user : userList){
            System.out.println(user.toString());
        }
        System.out.println("hibernate配置成功");
        return "hibernate";
    }
}

package com.hh.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/27 16:34
 */
@Controller
@RequestMapping(value="mybatis")
public class MybatisController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="test")
    public String test(){
        List<User> userList = userService.select();
        for(User user : userList){
            System.out.println(user.toString());
        }
        System.out.println("mybatis配置成功");
        return "mybatis";
    }
}

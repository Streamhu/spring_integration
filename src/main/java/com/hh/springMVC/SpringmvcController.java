package com.hh.springMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/27 16:03
 */
@Controller
@RequestMapping(value="/springMVC")
public class SpringmvcController {

    @RequestMapping(value="/test")
    public String test(){
        System.out.println("springMVC配置成功");
        return "springMVC";
    }
}

package com.hh.ehcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/29 9:23
 */
@Controller
@RequestMapping(value="/ehcache")
public class EhcacheController {

    @Autowired
    private EhCacheTestService ehCacheTestService;

    @RequestMapping(value="/test")
    public String test() throws InterruptedException {
        System.out.println("第一次调用：" + ehCacheTestService.getTimestamp("param"));
        Thread.sleep(2000);
        System.out.println("2秒之后调用：" + ehCacheTestService.getTimestamp("param"));
        Thread.sleep(11000);
        System.out.println("再过11秒之后调用：" + ehCacheTestService.getTimestamp("param"));
        return "ehcache";
    }
}
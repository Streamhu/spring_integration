package com.hh.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/28 14:00
 */
@Controller
@RequestMapping(value="redis")
public class RedisTemplateController {

    @Autowired
    protected RedisTemplate redisTemplate;

    @RequestMapping(value="template/test")
    public String test(){

/*
        RedisTemplate提供的常用方法
        opsForValue方法：操作具有简单值的条目
        opsForList方法：操作具有list值的条目
        opsForSet方法：操作具有set值的条目
        opsForZSet方法：操作具有ZSet值（排序的set）的条目
        opsForHash方法：操作具有hash值的条目
        boundValueOps方法：以绑定指定key的形式，操作具有简单值的条目
        boundListOps方法：以绑定指定key的形式，操作具有list值的条目
        boundSetOps方法：以绑定指定key的形式，操作具有set值的条目
        boundZSet方法：以绑定指定key的形式，操作具有ZSet值（排序的set）条目
        boundHashOps方法：以绑定指定key的形式，操作具有hash值得条目

*/

        redisTemplate.boundValueOps("zhangsan").set("张三");

        redisTemplate.boundValueOps("hh").set("hello world");
        String str = (String) redisTemplate.boundValueOps("hh").get();
        System.out.println(str);
        System.out.println("redisTemplate配置成功");
        return "redisTemplate";
    }
}

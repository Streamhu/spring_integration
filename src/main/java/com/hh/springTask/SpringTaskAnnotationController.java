package com.hh.springTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/29 10:26
 */
@Component("TaskJob")
public class SpringTaskAnnotationController {

    @Scheduled(cron = "0 0 0 * * ?")
    public void job1() {
        System.out.println("spring-task注解方式定时任务进行中。。。");
    }
}

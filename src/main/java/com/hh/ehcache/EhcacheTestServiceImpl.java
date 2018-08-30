package com.hh.ehcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/29 9:21
 */
@Service
public class EhcacheTestServiceImpl implements EhCacheTestService{

    @Override
    @Cacheable(value="cacheTest", key="#param")
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }

}

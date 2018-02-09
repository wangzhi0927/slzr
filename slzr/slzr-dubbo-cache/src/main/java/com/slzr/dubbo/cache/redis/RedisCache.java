package com.slzr.dubbo.cache.redis;

import com.alibaba.dubbo.common.URL;
import com.slzr.dubbo.cache.RemoteCache;
import com.slzr.dubbo.cache.remote.RedisClient;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/07/22  下午3:19
 */
public class RedisCache extends RemoteCache {
    
    @Override
    protected String getTagName() {
        return "redis";
    }

    protected  RedisCache(String cachedTarget,URL url){
        super(cachedTarget,url,new RedisClient());
    }
    
}

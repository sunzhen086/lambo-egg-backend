package com.lambo.cache.service.impl;

import com.lambo.cache.service.api.EhCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.stereotype.Service;

/**
 * cache工具类
 * @author sunzhen
 */
@Service
public class EhCacheSerivceImpl implements EhCacheService {

    @Autowired
    private AbstractCacheManager ehRedisCacheManager;

    /**
     * 获取缓存
     * @param cacheName
     * @return
     */
    @Override
    public Cache getCache(String cacheName) {
        Cache cache = ehRedisCacheManager.getCache(cacheName);
        if (null == cache) {
            return null;
        }
        return cache;
    }

    /**
     * 新增缓存记录
     * @param cacheName
     * @param key
     * @param value
     */
    @Override
    public void put(String cacheName, String key, Object value) {
        Cache cache = getCache(cacheName);
        if (null != cache) {
            cache.put(key,value);
        }
    }

    /**
     * 删除缓存记录
     * @param cacheName
     * @param key
     * @return
     */
    @Override
    public boolean remove(String cacheName, String key) {
        Cache cache = getCache(cacheName);
        if (null == cache) {
            return false;
        }
        cache.evict(key);
        return true;
    }

    /**
     * 删除全部缓存记录
     * @param cacheName
     * @return
     */
    @Override
    public void removeAll(String cacheName) {
        Cache cache = getCache(cacheName);
        if (null != cache) {
            cache.clear();
        }
    }

    /**
     * 获取缓存记录
     * @param cacheName
     * @param key
     * @return
     */
    @Override
    public Object get(String cacheName, String key) {
        Cache cache = getCache(cacheName);
        if (null == cache) {
            return null;
        }
        return cache.get(key).get();
    }


}

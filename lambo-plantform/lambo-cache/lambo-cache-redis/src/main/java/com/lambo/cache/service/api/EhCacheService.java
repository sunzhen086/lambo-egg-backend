package com.lambo.cache.service.api;


import org.springframework.cache.Cache;

/**
 * @author sunzhen
 * @ClassName EhCacheService
 * @Descirption TODO
 * @Date 2018/6/26 14:17
 **/
public interface EhCacheService {

    /**
     * 获取缓存实例
     * @param cacheName
     * @return
     */
    Cache getCache(String cacheName);

    /**
     * 设置缓存
     * @param cacheName
     * @param key
     * @param value
     */
    void put(String cacheName, String key, Object value);

    /**
     * 删除缓存
     * @param cacheName
     * @param key
     * @return
     */
    boolean remove(String cacheName, String key);

    /**
     * 清空缓存
     * @param cacheName
     */
    void removeAll(String cacheName);

    /**
     * 获取缓存值
     * @param cacheName
     * @param key
     * @return
     */
    Object get(String cacheName, String key);
}

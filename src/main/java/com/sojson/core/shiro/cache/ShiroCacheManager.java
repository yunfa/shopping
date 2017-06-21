package com.sojson.core.shiro.cache;

import org.apache.shiro.cache.Cache;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface ShiroCacheManager {

    <K, V> Cache<K, V> getCache(String name);

    void destroy();

}

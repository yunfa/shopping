package com.alpha.core.shiro.cache.impl;

import org.apache.shiro.cache.Cache;

import com.alpha.core.shiro.cache.JedisManager;
import com.alpha.core.shiro.cache.JedisShiroCache;
import com.alpha.core.shiro.cache.ShiroCacheManager;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public class JedisShiroCacheManager implements ShiroCacheManager {

	private JedisManager jedisManager;

	@Override
	public <K, V> Cache<K, V> getCache(String name) {
		return new JedisShiroCache<K, V>(name, getJedisManager());
	}

	@Override
	public void destroy() {
	}

	public JedisManager getJedisManager() {
		return jedisManager;
	}

	public void setJedisManager(JedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}
}

package com.edu.taotao.rest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.edu.taotao.rest.dao.IJedisDao;

import redis.clients.jedis.JedisCluster;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月5日
 * @description
 */
public class JedisClusterDaoImpl implements IJedisDao {

	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public Long del(String key) {
		return jedisCluster.del(key);
	}

	@Override
	public Long hSet(String hKey, String key, String value) {
		return jedisCluster.hset(hKey, key, value);
	}

	@Override
	public String hGet(String hKey, String key) {
		return jedisCluster.hget(hKey, key);
	}

	@Override
	public Long hDel(String hkey, String key) {
		return jedisCluster.hdel(hkey, key);
	}

	@Override
	public Long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public Long decr(String key) {
		return jedisCluster.decr(key);
	}

	@Override
	public Long expire(String key, Integer seconds) {
		return jedisCluster.expire(key, seconds);
	}

}

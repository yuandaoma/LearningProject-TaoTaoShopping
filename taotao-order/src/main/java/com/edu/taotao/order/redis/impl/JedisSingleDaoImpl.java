package com.edu.taotao.order.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.edu.taotao.order.redis.IJedisDao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月5日
 * @description
 */
public class JedisSingleDaoImpl implements IJedisDao {

	@Autowired
	private JedisPool jedisPool;

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String retVal = jedis.set(key, value);
		jedis.close();
		return retVal;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String retVal = jedis.get(key);
		jedis.close();
		return retVal;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long retVal = jedis.del(key);
		jedis.close();
		return retVal;
	}

	@Override
	public Long hSet(String hKey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long retVal = jedis.hset(hKey, key, value);
		jedis.close();
		return retVal;
	}

	@Override
	public String hGet(String hKey, String key) {
		Jedis jedis = jedisPool.getResource();
		String retVal = jedis.hget(hKey, key);
		jedis.close();
		return retVal;
	}

	@Override
	public Long hDel(String hKey, String key) {
		Jedis jedis = jedisPool.getResource();
		Long retVal = jedis.hdel(hKey, key);
		jedis.close();
		return retVal;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long retVal = jedis.incr(key);
		jedis.close();
		return retVal;
	}

	@Override
	public Long decr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long retVal = jedis.decr(key);
		jedis.close();
		return retVal;
	}

	@Override
	public Long expire(String key, Integer seconds) {
		Jedis jedis = jedisPool.getResource();
		Long retVal = jedis.expire(key, seconds);
		jedis.close();
		return retVal;
	}

}

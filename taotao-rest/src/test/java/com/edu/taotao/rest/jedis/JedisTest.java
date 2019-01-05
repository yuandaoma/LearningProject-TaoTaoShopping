package com.edu.taotao.rest.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description
 */
public class JedisTest {

	@Test
	public void testJedisSingle() throws Exception {
		Jedis jedis = new Jedis("192.168.37.129", 6379);
		jedis.set("jedisSingle", "This is a jedis Single test!");
		String retVal = jedis.get("jedisSingle");
		System.out.println("retVal = " + retVal);
		jedis.close();
	}

	@Test
	public void testJedisPool() throws Exception {
		JedisPool jedisPool = new JedisPool("192.168.37.129", 6379);
		Jedis jedis = jedisPool.getResource();
		jedis.set("jedisPool", "This is a jedisPool Test!");
		String retVal = jedis.get("jedisPool");
		System.out.println("retVal = " + retVal);
		jedisPool.close();
	}

	@Test
	public void testJedisCluster() throws Exception {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.37.129", 7001));
		nodes.add(new HostAndPort("192.168.37.129", 7002));
		nodes.add(new HostAndPort("192.168.37.129", 7003));
		nodes.add(new HostAndPort("192.168.37.129", 7004));
		nodes.add(new HostAndPort("192.168.37.129", 7005));
		nodes.add(new HostAndPort("192.168.37.129", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("jedisCluster", "This is a jediscluster test!");
		String retVal = cluster.get("jedisCluster");
		System.out.println("retVal = " + retVal);
		// 不要关闭
		cluster.close();
	}

	@Test
	public void testSpringJedisSingle() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool jedisPool = (JedisPool) context.getBean("jedisClient");
		Jedis jedis = jedisPool.getResource();
		String retVal = jedis.get("jedisPool");
		System.out.println("retVal = " + retVal);
	}

	@Test
	public void testSpringJedisCluster() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster cluster = (JedisCluster) context.getBean("jedisClient");
		cluster.set("desc", "testSpringJedisCluster");
		String retVal = cluster.get("desc");
		System.out.println("retVal = " + retVal);
	}

}

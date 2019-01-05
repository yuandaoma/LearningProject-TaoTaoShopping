package com.edu.taotao.sso.dao;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月5日
 * @description redis相关操作
 */
public interface IJedisDao {

	String set(String key, String value);

	String get(String key);

	Long del(String key);

	Long hSet(String hKey, String key, String value);

	String hGet(String hKey, String key);

	Long hDel(String hKey, String key);

	Long incr(String key);

	Long decr(String key);

	Long expire(String key, Integer seconds);

}

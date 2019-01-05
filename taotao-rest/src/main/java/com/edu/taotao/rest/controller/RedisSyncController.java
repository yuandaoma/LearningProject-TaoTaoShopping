package com.edu.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.taotao.rest.dao.IJedisDao;
import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月5日
 * @description
 */
@Controller
@RequestMapping("/redis/sync")
public class RedisSyncController {

	@Autowired
	private IJedisDao jedisDao;

	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@RequestMapping("/index/contentCategory/{catId}")
	@ResponseBody
	public TaotaoResult syncIndexPageContentCategoryInfo(@PathVariable Long catId) {
		try {
			jedisDao.hDel(INDEX_CONTENT_REDIS_KEY, catId + "");
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.toString());
		}
		return TaotaoResult.ok();
	}
}

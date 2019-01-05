package com.edu.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edu.taotao.rest.dao.IJedisDao;
import com.edu.taotao.rest.service.IContentService;
import com.google.gson.reflect.TypeToken;
import com.taotao.common.utils.GsonUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCriteria;
import com.taotao.pojo.TbContentCriteria.Criteria;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description
 */
@Service
public class ContentServiceImpl implements IContentService {

	@Autowired
	private TbContentMapper tbContentMapper;

	@Autowired
	private IJedisDao jedisDao;

	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public List<TbContent> listContentsByCategoryId(Long id) {
		String jedisRetVal;
		try {
			jedisRetVal = jedisDao.hGet(INDEX_CONTENT_REDIS_KEY, id + "");
			if (jedisRetVal != null) {
				return GsonUtil.getGson().fromJson(jedisRetVal, new TypeToken<List<TbContent>>() {
				}.getType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbContentCriteria example = new TbContentCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(id);
		List<TbContent> retList = tbContentMapper.selectByExample(example);
		try {
			String saveJedisJson = GsonUtil.getGson().toJson(retList);
			jedisDao.hSet(INDEX_CONTENT_REDIS_KEY, id + "", saveJedisJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}
}

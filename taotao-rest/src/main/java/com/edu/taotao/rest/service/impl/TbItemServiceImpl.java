package com.edu.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edu.taotao.rest.dao.IJedisDao;
import com.edu.taotao.rest.service.ITbItemService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.GsonUtil;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemCriteria;
import com.taotao.pojo.TbItemParamItemCriteria.Criteria;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月7日
 * @description 商品相关服务
 */
@Service
public class TbItemServiceImpl implements ITbItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Autowired
	private IJedisDao jedisDao;

	@Value("${REDIS_KEY_ITEM_INFO}")
	private String REDIS_KEY_ITEM_INFO;

	@Value("${REDIS_KEY_ITEM_DESC}")
	private String REDIS_KEY_ITEM_DESC;

	@Value("${REDIS_KEY_ITEM_PARAM}")
	private String REDIS_KEY_ITEM_PARAM;

	@Value("${REDIS_KEY_ITEM_EXPIRE}")
	private Integer REDIS_KEY_ITEM_EXPIRE;

	@Override
	public TaotaoResult findItemInfoById(Long itemId) {
		// 从缓存中查找
		try {
			String retJson = jedisDao.get(REDIS_KEY_ITEM_INFO + ":" + itemId);
			System.out.println("retJson = " + retJson);
			if (StringUtils.isNotBlank(retJson)) {
				return TaotaoResult.ok(GsonUtil.getGson().fromJson(retJson, TbItem.class));
			}
		} catch (JsonSyntaxException e1) {
			e1.printStackTrace();
		}

		TbItem item = itemMapper.selectByPrimaryKey(itemId);

		try {
			// 加入缓存，并设置过期时间
			jedisDao.set(REDIS_KEY_ITEM_INFO + ":" + itemId, GsonUtil.getGson().toJson(item));
			jedisDao.expire(REDIS_KEY_ITEM_INFO + ":" + itemId, REDIS_KEY_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(item);
	}

	@Override
	public TaotaoResult findItemDescById(Long itemId) {
		// 从缓存中查找
		try {
			String retJson = jedisDao.get(REDIS_KEY_ITEM_DESC + ":" + itemId);
			if (StringUtils.isNotBlank(retJson)) {
				return TaotaoResult.ok(GsonUtil.getGson().fromJson(retJson, TbItemDesc.class));
			}
		} catch (JsonSyntaxException e1) {
			e1.printStackTrace();
		}

		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);

		try {
			// 加入缓存，并设置过期时间
			jedisDao.set(REDIS_KEY_ITEM_DESC + ":" + itemId, GsonUtil.getGson().toJson(itemDesc));
			jedisDao.expire(REDIS_KEY_ITEM_DESC + ":" + itemId, REDIS_KEY_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(itemDesc);
	}

	@Override
	public TaotaoResult findItemParamItemById(Long itemId) {
		// 从缓存中查找
		try {
			String retJson = jedisDao.get(REDIS_KEY_ITEM_PARAM + ":" + itemId);
			if (StringUtils.isNotBlank(retJson)) {
				return TaotaoResult.ok(GsonUtil.getGson().fromJson(retJson, TbItemParamItem.class));
			}
		} catch (JsonSyntaxException e1) {
			e1.printStackTrace();
		}

		TbItemParamItemCriteria example = new TbItemParamItemCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> lists = itemParamItemMapper.selectByExampleWithBLOBs(example);
		TbItemParamItem itemParamItem = null;
		if (lists != null && lists.size() > 0) {
			itemParamItem = lists.get(0);
		}
		try {
			// 加入缓存，并设置过期时间
			jedisDao.set(REDIS_KEY_ITEM_PARAM + ":" + itemId, GsonUtil.getGson().toJson(itemParamItem));
			jedisDao.expire(REDIS_KEY_ITEM_PARAM + ":" + itemId, REDIS_KEY_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(itemParamItem);
	}

}

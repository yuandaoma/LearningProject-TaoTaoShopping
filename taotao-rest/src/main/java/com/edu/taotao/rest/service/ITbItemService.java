package com.edu.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月7日
 * @description
 */
public interface ITbItemService {

	/**
	 * 获取商品基本信息
	 * 
	 * @param itemId
	 * @return
	 */
	TaotaoResult findItemInfoById(Long itemId);

	/**
	 * 获取商品描述信息
	 * 
	 * @param itemId
	 * @return
	 */
	TaotaoResult findItemDescById(Long itemId);

	/**
	 * 获取商品规格参数信息
	 * 
	 * @param itemId
	 * @return
	 */
	TaotaoResult findItemParamItemById(Long itemId);

}

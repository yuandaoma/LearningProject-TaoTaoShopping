package com.edu.taotao.portal.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月7日
 * @description 
 */
public interface IItemService {

	/**
	 * 获取商品详情
	 * @param itemId
	 * @return
	 */
	TbItem findItemById(Long itemId);

	/**
	 *  
	 * @param itemId
	 * @return
	 */
	TbItemDesc findItemDescById(Long itemId);

	/**
	 * 获取商品规格信息
	 * @param itemId
	 * @return
	 */
	String findItemParamItemByItemId(Long itemId);

}

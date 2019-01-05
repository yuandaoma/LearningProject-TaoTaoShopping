package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月2日
 * @description 规格参数Service
 */
public interface ITbItemParamService {

	/**
	 * 返回规格参数的DataGrid数据
	 * @param page
	 * @param rows
	 * @return
	 */

	TaotaoResult listItemParam(Integer page, Integer rows);
	/**
	 * 返回制定商品类型的规格参数列表
	 * @param id
	 * @return
	 */
	TaotaoResult listItemParamByItemCatId(Long id);
	
	/**
	 * 保存某类商品的规格参数模版
	 * @param itemCatId
	 * @param paramData
	 * @return
	 */
	TaotaoResult saveItemParamByItemCatIdWithParamData(Long itemCatId, String paramData);

}

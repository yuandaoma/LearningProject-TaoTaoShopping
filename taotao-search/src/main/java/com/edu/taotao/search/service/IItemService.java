package com.edu.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月6日
 * @description 内容搜索Service
 */
public interface IItemService {

	/**
	 * 将数据库中的商品信息转到solr中
	 * 
	 * @return
	 */
	TaotaoResult transforItemsToSolr();

	/**
	 * 依据查询条件查询商品列表
	 * 
	 * @param condition
	 * @param page
	 * @param rows
	 * @return
	 */
	TaotaoResult queryItems(String condition, Integer page, Integer rows);

}

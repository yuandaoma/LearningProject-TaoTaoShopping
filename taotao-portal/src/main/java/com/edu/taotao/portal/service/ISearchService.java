package com.edu.taotao.portal.service;

import com.taotao.common.pojo.SolrSearchItemVO;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月7日
 * @description 搜索服务
 */
public interface ISearchService {

	/**
	 * 首页搜索商品
	 * @param queryCondition
	 * @param page
	 * @return
	 */
	SolrSearchItemVO listSearchResult(String queryCondition, Integer page);

}

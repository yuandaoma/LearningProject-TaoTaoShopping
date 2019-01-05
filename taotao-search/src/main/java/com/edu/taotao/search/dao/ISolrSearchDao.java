package com.edu.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.common.pojo.SolrSearchItemVO;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月6日
 * @description solr搜索相关
 */
public interface ISolrSearchDao {

	/**
	 * solr 查询结果
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	SolrSearchItemVO searchItems(SolrQuery query) throws Exception;

}

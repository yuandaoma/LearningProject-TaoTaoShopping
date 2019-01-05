package com.edu.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.taotao.search.dao.ISolrSearchDao;
import com.taotao.common.pojo.SearchItemVO;
import com.taotao.common.pojo.SolrSearchItemVO;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月6日
 * @description
 */
@Repository
public class SolrSearchDaoImpl implements ISolrSearchDao {

	@Autowired
	private SolrServer solrServer;

	@Override
	public SolrSearchItemVO searchItems(SolrQuery query) throws Exception {
		SolrSearchItemVO retVO = new SolrSearchItemVO();
		// 查询
		QueryResponse response = solrServer.query(query);
		// 获取查询结果
		SolrDocumentList results = response.getResults();
		retVO.setRecordCount(results.getNumFound());
		List<SearchItemVO> itemVOs = new ArrayList<>();
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		results.forEach(result -> {
			SearchItemVO vo = new SearchItemVO();
			vo.setId((String) result.get("id"));
			List<String> list = highlighting.get(result.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size() > 0) {
				title = list.get(0);
			} else {
				title = (String) result.get("item_title");
			}
			vo.setTitle(title);
			vo.setImage((String) result.get("item_image"));
			vo.setPrice((Long) result.get("item_price"));
			vo.setSellPoint((String) result.get("item_sell_point"));
			vo.setItemDesc((String) result.get("item_desc"));
			vo.setCategoryName((String) result.get("item_category_name"));
			itemVOs.add(vo);
		});
		retVO.setItemList(itemVOs);
		return retVO;
	}

}

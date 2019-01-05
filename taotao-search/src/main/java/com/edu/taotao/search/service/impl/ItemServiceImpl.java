package com.edu.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.taotao.search.dao.IItemMapper;
import com.edu.taotao.search.dao.impl.SolrSearchDaoImpl;
import com.edu.taotao.search.service.IItemService;
import com.taotao.common.pojo.SearchItemVO;
import com.taotao.common.pojo.SolrSearchItemVO;
import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月6日
 * @description
 */
@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	private IItemMapper itemMapper;

	@Autowired
	private SolrSearchDaoImpl solrSearchDao;

	@Autowired
	private SolrServer solrServer;

	@Override
	public TaotaoResult transforItemsToSolr() {
		List<SearchItemVO> items = itemMapper.listSolrAnalyItems();
		try {
			items.forEach(item -> {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", item.getId());
				document.addField("item_title", item.getTitle());
				document.addField("item_sell_point", item.getSellPoint());
				document.addField("item_category_name", item.getCategoryName());
				document.addField("item_price", item.getPrice());
				document.addField("item_desc", item.getItemDesc());
				document.addField("item_image", item.getImage());
				try {
					solrServer.add(document);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(400, "数据转存至solr中出错");
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult queryItems(String condition, Integer page, Integer rows) {
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery(condition);
		// 设置起始位置
		query.setStart((page - 1) * rows);
		// 设置查询的条数
		query.setRows(rows);
		// 设置默认搜索域
		query.set("df", "item_keywords");
		// 设置高亮显示
		query.setHighlight(true);
		// 设置高亮显示字段
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		SolrSearchItemVO result;
		try {
			result = solrSearchDao.searchItems(query);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(250, "获取solr数据失败");
		}
		Long totalCount = result.getRecordCount();
		Long pageCount = totalCount / rows + (totalCount % rows == 0 ? 0 : 1);
		result.setPageCount(pageCount);
		result.setCurPage(page.longValue());
		return TaotaoResult.ok(result);
	}
}

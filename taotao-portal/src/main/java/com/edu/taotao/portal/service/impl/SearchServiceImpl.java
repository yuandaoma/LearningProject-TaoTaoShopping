package com.edu.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edu.taotao.portal.service.ISearchService;
import com.google.gson.Gson;
import com.taotao.common.pojo.SolrSearchItemVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.GsonUtil;
import com.taotao.common.utils.HttpUtil;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月7日
 * @description
 */
@Service
public class SearchServiceImpl implements ISearchService {

	@Value("${solr.url}")
	private String solrUrl;

	@Value("${solr.searchInterface}")
	private String searchInterface;

	@Override
	public SolrSearchItemVO listSearchResult(String queryCondition, Integer page) {
		Map<String, String> param = new HashMap<>();
		param.put("q", queryCondition);
		param.put("page", page + "");
		String retJson = HttpUtil.doGet(solrUrl + searchInterface, param);
		if (StringUtils.isBlank(retJson)) {
			throw new RuntimeException("solr服务出错");
		}
		TaotaoResult taotaoResult = GsonUtil.getGson().fromJson(retJson, TaotaoResult.class);
		if (taotaoResult.getStatus() == 200) {
			String voJson = GsonUtil.getGson().toJson(taotaoResult.getData());
			return GsonUtil.getGson().fromJson(voJson, SolrSearchItemVO.class);
		}
		return null;
	}

}

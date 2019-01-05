package com.edu.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.taotao.portal.service.ISearchService;
import com.taotao.common.pojo.SolrSearchItemVO;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月7日
 * @description
 */
@Controller
public class IndexSearchController {

	@Autowired
	private ISearchService searchService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String listSolrSearchResult(@RequestParam("q") String queryCondition,
			@RequestParam(defaultValue = "1") Integer page, Model model) {
		try {
			queryCondition = new String(queryCondition.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SolrSearchItemVO vo = searchService.listSearchResult(queryCondition, page);
		model.addAttribute("query", queryCondition);
		model.addAttribute("totalPages", vo.getPageCount());
		model.addAttribute("itemList", vo.getItemList());
		model.addAttribute("page", page);
		return "search";
	}

}

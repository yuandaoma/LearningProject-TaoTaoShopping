package com.edu.taotao.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月7日
 * @description
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	@ResponseBody
	public String welcomtToSolrService() {
		return "Welcomt to use solr service!";
	}
}

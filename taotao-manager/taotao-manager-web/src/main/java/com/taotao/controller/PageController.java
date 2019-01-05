package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月1日
 * @description 
 */
@Controller
public class PageController {

	/**
	 * 跳转到后台首页
	 * @return
	 */
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	/**
	 * 后台各个页面之间的跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/{page}",method = RequestMethod.GET)
	public String page(@PathVariable String page) {
		return page;
	}
	
}

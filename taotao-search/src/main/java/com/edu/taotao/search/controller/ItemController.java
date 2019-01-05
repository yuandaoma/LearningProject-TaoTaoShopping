package com.edu.taotao.search.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.taotao.search.service.IItemService;
import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月6日
 * @description
 */
@Controller
public class ItemController {

	@Autowired
	private IItemService itemService;

	@RequestMapping("/transforItemsToSolr")
	@ResponseBody
	public TaotaoResult transforItemsToSolr() {
		return itemService.transforItemsToSolr();
	}

	@RequestMapping(value = "/queryItem", method = RequestMethod.GET)
	@ResponseBody
	public TaotaoResult queryItems(@RequestParam("q") String condition, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "60") Integer rows) {
		if (StringUtils.isBlank(condition)) {
			return TaotaoResult.build(250, "请输入查询条件");
		}
		try {
			condition = new String(condition.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return TaotaoResult.build(250, "转换个字符编码都能错，你砸不上天呢！");
		}
		return itemService.queryItems(condition, page, rows);
	}

}

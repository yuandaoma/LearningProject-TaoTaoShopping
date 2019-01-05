package com.edu.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.taotao.rest.service.ITbItemService;
import com.google.gson.annotations.Expose;
import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月7日
 * @description
 */
@Controller
@RequestMapping("/item")
public class TbItemController {

	@Autowired
	private ITbItemService tbItemService;

	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public TaotaoResult findItemInfoById(@PathVariable Long itemId) {
		return tbItemService.findItemInfoById(itemId);
	}

	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult findItemDescById(@PathVariable Long itemId) {
		return tbItemService.findItemDescById(itemId);
	}

	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public TaotaoResult findItemParamItemById(@PathVariable Long itemId) {
		return tbItemService.findItemParamItemById(itemId);
	}
}

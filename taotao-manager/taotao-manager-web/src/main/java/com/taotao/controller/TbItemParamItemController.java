package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.service.ITbItemParamItemService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月3日
 * @description
 */
@Controller
public class TbItemParamItemController {

	@Autowired
	private ITbItemParamItemService tbItemParamItemService;

	@RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
	public String findItemParamItemByItemId(@PathVariable Long itemId, Model mv) {
		mv.addAttribute("itemParam", tbItemParamItemService.findItemParamByItemId(itemId));
		return "item";
	}

}

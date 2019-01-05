package com.edu.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.taotao.portal.service.IItemService;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月7日
 * @description
 */
@Controller
public class TbItemController {

	@Autowired
	private IItemService itemService;

	@RequestMapping("/item/{itemId}")
	public String findItemById(@PathVariable String itemId, Model model) {
		TbItem item = itemService.findItemById(Long.parseLong(itemId));
		model.addAttribute("item", item);
		return "item";
	}

	@RequestMapping(value = "/item/desc/{itemId}", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String findItemDescById(@PathVariable Long itemId) {
		TbItemDesc itemDesc = itemService.findItemDescById(itemId);
		return itemDesc.getItemDesc();
	}

	@RequestMapping(value = "/item/param/{itemId}", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String findItemParamItemByItemId(@PathVariable Long itemId) {
		return itemService.findItemParamItemByItemId(itemId);
	}

}

package com.taotao.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.pojo.TreeNodeVO;
import com.taotao.common.pojo.DataGridDataVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ITbItemService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月1日
 * @description
 */
@Controller
public class TbItemController {

	@Autowired
	private ITbItemService tbItemService;

	@RequestMapping(value = "/item/findOne/{id}", method = RequestMethod.GET)
	@ResponseBody
	public TbItem findById(@PathVariable Long id) throws Exception {
		return tbItemService.findOne(id);
	}

	@RequestMapping(value = "/item/list", method = RequestMethod.GET)
	@ResponseBody
	public DataGridDataVO listItem(Integer page, Integer rows) {
		return tbItemService.listItem(page, rows);
	}

	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveItem(TbItem item, String desc, String itemParams) {
		return tbItemService.saveItem(item, desc, itemParams);
	}

	@RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteItems(String ids) {
		String[] split = ids.split(",");
		List<Long> deleteIds = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			deleteIds.add(Long.parseLong(split[i]));
		}
		return tbItemService.deleteItems(deleteIds);
	}

}

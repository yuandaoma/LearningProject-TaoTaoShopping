package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.DataGridDataVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ITbItemParamService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月2日
 * @description 规格参数Controller
 */
@Controller
public class TbItemParamController {

	@Autowired
	private ITbItemParamService tbItemParamService;

	/**
	 * http://localhost:8080/item/param/list?page=1&rows=30
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/item/param/list", method = RequestMethod.GET)
	@ResponseBody
	public TaotaoResult listItemResult(Integer page, Integer rows) {
		return tbItemParamService.listItemParam(page, rows);
	}

	/**
	 * /item/param/query/itemcatid/
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item/param/query/itemcatid/{id}", method = RequestMethod.GET)
	@ResponseBody
	public TaotaoResult listItemParamByItemCatId(@PathVariable Long id) {
		return tbItemParamService.listItemParamByItemCatId(id);
	}

	/**
	 * /item/param/save/
	 */
	@RequestMapping(value = "/item/param/save/{itemCatId}", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveItemParamByItemCatIdWithParamData(@PathVariable Long itemCatId,String paramData) {
		return tbItemParamService.saveItemParamByItemCatIdWithParamData(itemCatId,paramData);
	}

}

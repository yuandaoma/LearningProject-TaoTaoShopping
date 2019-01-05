package com.edu.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.taotao.rest.service.ITbItemCatService;
import com.edu.taotao.rest.vo.ItemCatVO;
import com.taotao.common.utils.GsonUtil;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月3日
 * @description 商品分类表
 */
@Controller
public class TbItemCatController {

	@Autowired
	private ITbItemCatService tbItemCatService;

	@RequestMapping(value = "/itemCat/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String listItemCat(String callback) {
		ItemCatVO retVal = tbItemCatService.listItemCat();
		String retJson = GsonUtil.getGson().toJson(retVal);
		return callback + "(" + retJson + ");";
	}

	// 方法二，jsonp自动拼装callback函数段
	@RequestMapping("/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback) {
		ItemCatVO retVal = tbItemCatService.listItemCat();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(retVal);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}

}

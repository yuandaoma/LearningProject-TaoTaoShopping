package com.edu.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.taotao.rest.service.IContentService;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description 首页大广告位的显示
 */
@Controller
public class TbContentController {

	@Autowired
	private IContentService contentService;

	@RequestMapping(value = "/content/category/{id}", method = RequestMethod.GET)
	@ResponseBody
	public TaotaoResult listContentsByCategoryId(@PathVariable Long id) {
		List<TbContent> contents = contentService.listContentsByCategoryId(id);
		return TaotaoResult.ok(contents);
	}

}

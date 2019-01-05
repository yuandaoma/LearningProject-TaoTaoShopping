package com.taotao.controller;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.DataGridDataVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ITbContentService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description 内容控制器
 */
@Controller
public class TbContentController {

	@Autowired
	private ITbContentService tbContentService;

	@RequestMapping(value = "/content/query/list", method = RequestMethod.GET)
	@ResponseBody
	public DataGridDataVO pageContent(Long categoryId, Integer page, Integer rows) {
		return tbContentService.pageContentByCategoryId(categoryId, page, rows);
	}

	@RequestMapping(value = "/content/save", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveContent(TbContent content) {
		return tbContentService.saveContent(content);
	}

	@RequestMapping(value = "/content/delete", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteContents(String ids) {
		String[] split = ids.split(",");
		List<Long> deleteIds = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			deleteIds.add(Long.parseLong(split[i]));
		}
		return tbContentService.deleteContents(deleteIds);
	}

}

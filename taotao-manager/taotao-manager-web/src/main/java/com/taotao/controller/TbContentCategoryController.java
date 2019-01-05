package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNodeVO;
import com.taotao.service.ITbContentCategoryService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description 后台对前台首页进行内容管理
 */
@Controller
@RequestMapping("/content/category")
public class TbContentCategoryController {

	@Autowired
	private ITbContentCategoryService tbContentCategoryService;

	/***
	 * 展示列表
	 * 
	 * @param parentId
	 * @return @see{TreeNode}
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeNodeVO> listContentCategoryByParentId(@RequestParam(value = "id", defaultValue = "0") Long parentId)
			throws Exception {
		return tbContentCategoryService.listContentCategoryByParentId(parentId);
	}

	/**
	 * 创建一个新的节点
	 * 
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId, String name) {
		return tbContentCategoryService.createContentCategory(parentId, name);
	}

	/**
	 * 删除节点
	 * 
	 * @param parentId
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(@RequestParam(value = "parentId", defaultValue = "0") Long parentId,
			@RequestParam("id") Long id) {
		return tbContentCategoryService.deleteContentCategory(parentId, id);
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public TaotaoResult updateContentCategoryNameById(Long id, String name) throws Exception {
		return tbContentCategoryService.updateContentCategoryNameById(id,name);
	}
}

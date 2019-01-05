package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TreeNodeVO;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ITbItemCatService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月1日
 * @description 商品分类
 */
@Controller
public class TbItemCatController {

	@Autowired
	private ITbItemCatService tbItemCatService;

	@RequestMapping(value = "/item/cat/list", method = RequestMethod.POST)
	@ResponseBody
	// 如果id为null使用默认值 ，也就是parentId为0的分类列表
	public List<TreeNodeVO> findItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId)
			throws Exception {
		List<TbItemCat> result = tbItemCatService.findItemCatListByParentId(parentId);
		List<TreeNodeVO> items = new ArrayList<>();
		result.forEach(item -> {
			TreeNodeVO itemVO = new TreeNodeVO();
			itemVO.setId(item.getId());
			itemVO.setText(item.getName());
			itemVO.setState(item.getIsParent() ? "closed" : "open");
			items.add(itemVO);
		});
		return items;
	}

}

package com.edu.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.taotao.portal.service.IADService;
import com.taotao.common.pojo.ADItemVO;
import com.taotao.common.utils.GsonUtil;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月3日
 * @description
 */
@Controller
public class PageController {

	@Autowired
	private IADService adService;

	@RequestMapping("/index")
	public String getIndex(Model model) {
		List<ADItemVO> adVos = adService.listAdItems();
		if (adVos != null && adVos.size() > 0) {
			model.addAttribute("ad1", GsonUtil.getGson().toJson(adVos));
		} else {
			model.addAttribute("ad1", null);
		}
		return "index";
	}
	

}

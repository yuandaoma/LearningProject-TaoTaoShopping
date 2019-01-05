package com.edu.taotao.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月12日
 * @description
 */
@Controller
public class PageController {

	@RequestMapping("/")
	@ResponseBody
	public String taotaoOrderWelcomePage() {
		return "welcom to taotao order system!";
	}

}

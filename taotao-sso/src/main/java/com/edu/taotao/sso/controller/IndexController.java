package com.edu.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月8日
 * @description
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	@ResponseBody
	public String login() {
		return "welocome sso login page";
	}
	
	@RequestMapping("/page/register")
	public String showRegisterJsp() {
		return "register";
	}

	@RequestMapping("/page/login")
	public String showLoginJsp(String redirect,Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
}

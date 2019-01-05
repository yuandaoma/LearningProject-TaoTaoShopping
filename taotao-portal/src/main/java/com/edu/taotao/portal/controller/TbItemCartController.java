package com.edu.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.taotao.portal.service.ITbItemCartService;
import com.taotao.common.pojo.ItemCartVO;
import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月10日
 * @description 购物车请求
 */
@Controller
@RequestMapping("/cart")
public class TbItemCartController {

	@Autowired
	private ITbItemCartService tbItemCartService;

	@RequestMapping("/cart")
	public String showCartJsp(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ItemCartVO> vos = tbItemCartService.findCartItems(request, response);
		model.addAttribute("cartList", vos);
		return "cart";
	}

	@RequestMapping("/success")
	public String addCartSuccsss() {
		return "cartSuccess";
	}
	
	
	
	@RequestMapping("/add/{itemId}")
	public String addItemToCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		tbItemCartService.addItemToCart(itemId, num, request, response);
		return "redirect:/cart/success.html";
	}

	@RequestMapping("/delete/{id}")
	public String deleteCartItemById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		tbItemCartService.deleteCartItemById(id, request, response);
		return "redirect:/cart/cart.html";
	}

}

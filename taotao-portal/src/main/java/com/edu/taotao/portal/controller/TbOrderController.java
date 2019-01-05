package com.edu.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.taotao.portal.dto.OrderDTO;
import com.edu.taotao.portal.service.IOrderService;
import com.edu.taotao.portal.service.ITbItemCartService;

import com.taotao.common.pojo.ItemCartVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月12日
 * @description 订单处理
 */
@Controller
public class TbOrderController {

	@Autowired
	private ITbItemCartService tbItemCartService;
	
	@Autowired
	private IOrderService orderService;
	
	

	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ItemCartVO> items = tbItemCartService.findCartItems(request, response);
		model.addAttribute("cartList", items);
		return "order-cart";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createOrder(@RequestBody OrderDTO orderDTO, Model model,HttpServletRequest request) {
		TbUser user = (TbUser) request.getAttribute("user");
		orderDTO.setUserId(user.getId());
		orderDTO.setBuyerNick(user.getUsername());
		TaotaoResult result = orderService.createOrder(orderDTO);
		return "success";
	}
}

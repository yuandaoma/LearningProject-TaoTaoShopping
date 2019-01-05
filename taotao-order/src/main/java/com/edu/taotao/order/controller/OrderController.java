package com.edu.taotao.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.taotao.order.dto.OrderDTO;
import com.edu.taotao.order.service.IOrderService;
import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月12日
 * @description
 */
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService orderService;
	


	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createOrder(@RequestBody OrderDTO orderDTO) {
		TaotaoResult result = orderService.saveOrder(orderDTO);
		return result;
	}
	
	@RequestMapping(value = "/info/{orderId}",method = RequestMethod.GET)
	@ResponseBody
	public TaotaoResult findOrderInfoByOrderId(@PathVariable String orderId) {
		return orderService.findOrderInfoByOrderId(orderId);
	}



}

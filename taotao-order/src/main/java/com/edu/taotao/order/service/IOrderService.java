package com.edu.taotao.order.service;

import com.edu.taotao.order.dto.OrderDTO;
import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月12日
 * @description 
 */
public interface IOrderService {

	/**
	 * 保存订单信息
	 * @param orderDTO
	 * @return
	 */
	TaotaoResult saveOrder(OrderDTO orderDTO);

	/**
	 * 依据订单ID查询订单
	 * @param orderId
	 * @return
	 */
	TaotaoResult findOrderInfoByOrderId(String orderId);

}

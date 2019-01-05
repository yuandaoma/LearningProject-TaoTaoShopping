package com.edu.taotao.portal.service;

import com.edu.taotao.portal.dto.OrderDTO;
import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月12日
 * @description 
 */
public interface IOrderService {

	/**
	 * 创建订单
	 * 
	 * @param orderDTO
	 * @return
	 */
	TaotaoResult createOrder(OrderDTO orderDTO);
}

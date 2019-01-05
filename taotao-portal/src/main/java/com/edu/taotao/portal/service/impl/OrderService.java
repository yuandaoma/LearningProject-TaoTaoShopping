package com.edu.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edu.taotao.portal.dto.OrderDTO;
import com.edu.taotao.portal.service.IOrderService;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.GsonUtil;
import com.taotao.common.utils.HttpUtil;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月12日
 * @description 
 */
@Service
public class OrderService implements IOrderService {
	
	@Value("${order.url}")
	private String orderUrl;
	@Value("${orderApi.create}")
	private String orderApiCreate;

	@Override
	public TaotaoResult createOrder(OrderDTO orderDTO) {
		String transferJson = GsonUtil.getGson().toJson(orderDTO);
		String retJson = HttpUtil.doPostJson(orderUrl + orderApiCreate, transferJson);
		TaotaoResult retResult = GsonUtil.getGson().fromJson(retJson, TaotaoResult.class);
		if (retResult.getStatus() == 200) {
			return TaotaoResult.ok(retResult.getData());
		}
		return TaotaoResult.ok();
	}

}

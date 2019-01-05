package com.edu.taotao.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edu.taotao.order.dto.OrderDTO;
import com.edu.taotao.order.redis.IJedisDao;
import com.edu.taotao.order.service.IOrderService;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderItemCriteria;
import com.taotao.pojo.TbOrderItemCriteria.Criteria;
import com.taotao.pojo.TbOrderShipping;
import com.taotao.pojo.TbOrderShippingCriteria;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月12日
 * @description 订单服务
 */
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private TbOrderMapper tbOrderMapper;

	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;

	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper;

	@Autowired
	private IJedisDao jedisDao;

	@Value("${REDIS_KEY_ORDER_ID}")
	private String REDIS_KEY_ORDER_ID;

	@Value("${REDIS_KEY_ORDER_ID_INIT_DATA}")
	private String REDIS_KEY_ORDER_ID_INIT_DATA;

	@Value("${REDIS_KEY_ORDER_DETAIL_ID}")
	private String REDIS_KEY_ORDER_DETAIL_ID;

	@Value("${REDIS_KET_ORDER_DETAIL_ID_INIT_DATA}")
	private String REDIS_KET_ORDER_DETAIL_ID_INIT_DATA;

	@Override
	public TaotaoResult saveOrder(OrderDTO orderDTO) {
		String orderId = jedisDao.get(REDIS_KEY_ORDER_ID);
		if (orderId == null) {
			orderId = jedisDao.set(REDIS_KEY_ORDER_ID, REDIS_KEY_ORDER_ID_INIT_DATA);
		}
		orderId = jedisDao.incr(REDIS_KEY_ORDER_ID).toString();
		TbOrder order = orderDTO;
		Date date = new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		order.setOrderId(orderId);
		// 0：未评价，1：已评价
		order.setBuyerRate(0);
		order.setStatus(1);
		System.out.println("order = " + order);
		tbOrderMapper.insert(order);
		List<TbOrderItem> orderItems = orderDTO.getOrderItems();
		for (TbOrderItem item : orderItems) {
			Long orderItemId = jedisDao.incr(REDIS_KEY_ORDER_DETAIL_ID);
			item.setId(orderItemId + "");
			item.setOrderId(orderId);
			tbOrderItemMapper.insert(item);
		}
		TbOrderShipping orderShipping = orderDTO.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		tbOrderShippingMapper.insert(orderShipping);
		return TaotaoResult.ok(orderId);
	}

	@Override
	public TaotaoResult findOrderInfoByOrderId(String orderId) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO = (OrderDTO) tbOrderMapper.selectByPrimaryKey(orderId);
		List<TbOrderItem> orderItems = new ArrayList<>();
		TbOrderItemCriteria example = new TbOrderItemCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		orderItems = tbOrderItemMapper.selectByExample(example);
		orderDTO.setOrderItems(orderItems);
		TbOrderShippingCriteria shippingExample = new TbOrderShippingCriteria();
		com.taotao.pojo.TbOrderShippingCriteria.Criteria shippingCriteria = shippingExample.createCriteria();
		shippingCriteria.andOrderIdEqualTo(orderId);
		List<TbOrderShipping> orderShippings = tbOrderShippingMapper.selectByExample(shippingExample);
		if (orderShippings == null || orderShippings.size() != 1) {
			orderDTO.setOrderShipping(null);
		}
		orderDTO.setOrderShipping(orderShippings.get(0));
		return TaotaoResult.ok(orderDTO);
	}

}

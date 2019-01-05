package com.edu.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.dto.OrderDTO;
import com.taotao.common.pojo.ItemCartVO;
import com.taotao.common.pojo.TaotaoResult;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月10日
 * @description
 */
public interface ITbItemCartService {

	/**
	 * 添加商品到购物车
	 * 
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult addItemToCart(Long itemId, int num, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 删除购物车中的商品
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult deleteCartItemById(Long id, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 获取购物车中的商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	List<ItemCartVO> findCartItems(HttpServletRequest request, HttpServletResponse response);



}

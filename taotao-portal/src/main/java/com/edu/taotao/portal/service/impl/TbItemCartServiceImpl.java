package com.edu.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edu.taotao.portal.service.ITbItemCartService;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.taotao.common.pojo.ItemCartVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.GsonUtil;
import com.taotao.common.utils.HttpUtil;
import com.taotao.pojo.TbItem;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月10日
 * @description 购物车实现
 */
@Service
public class TbItemCartServiceImpl implements ITbItemCartService {

	@Value("${rest.url}")
	private String restUrl;

	@Value("${restApi.itemInfo}")
	private String restApiItemInfo;

	@Override
	public TaotaoResult addItemToCart(Long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		// 获取cookies中是否有
		String ttCart = CookieUtils.getCookieValue(request, "TT_CART");
		List<Integer> flag = new ArrayList<>();
		List<ItemCartVO> cartVOs;
		if (ttCart == null) {// 购物车中没有，设置为空的购物车
			cartVOs = new ArrayList<>();
		} else {// 购物车中存在商品，查看是否存在要添加的商品
			cartVOs = GsonUtil.getGson().fromJson(ttCart, new TypeToken<List<ItemCartVO>>() {
			}.getType());
			List<ItemCartVO> exist = cartVOs.stream().map(item -> {
				if (item.getId() == itemId) {
					item.setNum(item.getNum() + num);
					flag.add(1);
					return item;
				}
				return item;

			}).collect(Collectors.toList());
			CookieUtils.setCookie(request, response, "TT_CART", GsonUtil.getGson().toJson(exist));
		}
		// 查询商品信息
		if (flag.size() == 1 && flag.get(0) == 1) {// Cookies中存在商品信息
			return TaotaoResult.ok();
		} else {
			String retItemJson = HttpUtil.doGet(restUrl + restApiItemInfo + itemId);
			TaotaoResult result = GsonUtil.getGson().fromJson(retItemJson, TaotaoResult.class);
			if (result.getStatus() == 200) {
				String retDataJson = GsonUtil.getGson().toJson(result.getData());
				TbItem item = GsonUtil.getGson().fromJson(retDataJson, TbItem.class);
				ItemCartVO cartVO = new ItemCartVO();
				cartVO.setId(item.getId());
				cartVO.setImage(item.getImage());
				cartVO.setNum(num);
				cartVO.setPrice(item.getPrice());
				cartVO.setTitle(item.getTitle());
				cartVOs.add(cartVO);
				CookieUtils.setCookie(request, response, "TT_CART", GsonUtil.getGson().toJson(cartVOs));
			} else {
				return TaotaoResult.build(400, "taotao-rest未能获取商品信息");
			}
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteCartItemById(Long id, HttpServletRequest request, HttpServletResponse response) {
		String cartCookiesJson = CookieUtils.getCookieValue(request, "TT_CART");
		List<ItemCartVO> vos = GsonUtil.getGson().fromJson(cartCookiesJson, new TypeToken<List<ItemCartVO>>() {
		}.getType());
		List<ItemCartVO> deletedList = vos.stream().filter(cart -> {
			return !(cart.getId() == id);
		}).collect(Collectors.toList());
		CookieUtils.setCookie(request, response, "TT_CART", GsonUtil.getGson().toJson(deletedList));
		return TaotaoResult.ok();
	}

	@Override
	public List<ItemCartVO> findCartItems(HttpServletRequest request, HttpServletResponse response) {
		String retJson = CookieUtils.getCookieValue(request, "TT_CART");
		if (retJson == null) {
			return new ArrayList<>();
		}
		try {
			List<ItemCartVO> vos = GsonUtil.getGson().fromJson(retJson, new TypeToken<List<ItemCartVO>>() {
			}.getType());
			return vos;
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

}

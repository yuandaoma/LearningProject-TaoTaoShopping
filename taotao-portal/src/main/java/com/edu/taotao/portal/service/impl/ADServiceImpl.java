package com.edu.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edu.taotao.portal.service.IADService;
import com.google.gson.reflect.TypeToken;
import com.taotao.common.pojo.ADItemVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.GsonUtil;
import com.taotao.common.utils.HttpUtil;
import com.taotao.pojo.TbContent;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description
 */
@Service
public class ADServiceImpl implements IADService {

	@Value("${rest.url}")
	private String restUrl;

	@Value("${restApi.adList}")
	private String adList;

	@Override
	public List<ADItemVO> listAdItems() {
		String retAdItems = HttpUtil.doGet(restUrl + adList);
		TaotaoResult result = GsonUtil.getGson().fromJson(retAdItems, TaotaoResult.class);
		if (result.getStatus() == 200) {
			String retJson = GsonUtil.getGson().toJson(result.getData());
			List<TbContent> contents = GsonUtil.getGson().fromJson(retJson, new TypeToken<List<TbContent>>() {
			}.getType());
			List<ADItemVO> vos = new ArrayList<>();
			contents.forEach(content -> {
				ADItemVO vo = new ADItemVO();
				vo.setHeight(240);
				vo.setWidth(670);
				vo.setSrc(content.getPic());
				vo.setHeightB(240);
				vo.setWidthB(550);
				vo.setSrcB(content.getPic2());
				vo.setAlt(content.getTitleDesc());
				vo.setHref(content.getUrl());
				vos.add(vo);
			});
			return vos;
		}
		return null;
	}

}

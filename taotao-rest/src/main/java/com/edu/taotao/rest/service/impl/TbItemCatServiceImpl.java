package com.edu.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.taotao.rest.service.ITbItemCatService;
import com.edu.taotao.rest.vo.ItemCatVO;
import com.edu.taotao.rest.vo.ItemCatVO.ItemCat;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatCriteria;
import com.taotao.pojo.TbItemCatCriteria.Criteria;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月3日
 * @description
 */
@Service
public class TbItemCatServiceImpl implements ITbItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public ItemCatVO listItemCat() {
		ItemCatVO vo = new ItemCatVO();
		vo.setData(getItemCatData(0L));
		return vo;
	}

	private List<Object> getItemCatData(Long parentId) {
		List<Object> retList = new ArrayList<>();
		TbItemCatCriteria example = new TbItemCatCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> parentItems = tbItemCatMapper.selectByExample(example);
		int count = 0;
		for (TbItemCat parent : parentItems) {
			// 是祖先节点
			if (parent.getIsParent()) {

				if (count++ >= 14) {
					break;
				}
				ItemCat itemCat = new ItemCat();
				itemCat.setUrl("/products/" + parent.getId() + ".html");
				if (parent.getParentId().equals(0)) {
					itemCat.setName("<a href='/products/" + parent.getId() + ".html>" + parent.getName() + "</a>");
				} else {
					itemCat.setName(parent.getName());
				}
				itemCat.setItems(getItemCatData(parent.getId()));
				retList.add(itemCat);
			} else {// 是子节点
				retList.add("/products/" + parent.getId() + ".html|" + parent.getName());
			}

		}
		return retList;
	}

}

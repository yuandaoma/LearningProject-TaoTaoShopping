package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatCriteria;
import com.taotao.pojo.TbItemCatCriteria.Criteria;
import com.taotao.service.ITbItemCatService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月1日
 * @description 
 */
@Service
public class TbItemCatServiceImpl implements ITbItemCatService{
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public List<TbItemCat> findItemCatListByParentId(Long parentId) throws Exception{
		TbItemCatCriteria itemCatCriteria = new TbItemCatCriteria();
		Criteria criteria = itemCatCriteria.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(itemCatCriteria);
		return list;
	}
	
	

}

package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.DataGridDataVO;
import com.taotao.common.pojo.ItemParamVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamCriteria;
import com.taotao.pojo.TbItemParamCriteria.Criteria;
import com.taotao.service.ITbItemParamService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月2日
 * @description 
 */
@Service
public class TbItemParamServiceImpl implements ITbItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public TaotaoResult listItemParam(Integer page, Integer rows) {
		TbItemParamCriteria example = new TbItemParamCriteria();
		PageHelper.startPage(page,rows);
		List<TbItemParam> list = itemParamMapper.selectByExample(example);
		PageInfo<TbItemParam> paramLists = new PageInfo<>(list);
		DataGridDataVO vo = new DataGridDataVO();
		List<ItemParamVO> result = new ArrayList<>();
		list.forEach(param -> {
			ItemParamVO paramVO = new ItemParamVO();
			BeanUtils.copyProperties(param, paramVO);
			paramVO.setItemCatName(tbItemCatMapper.selectItemCatNameById(param.getItemCatId()));
			result.add(paramVO);
		});
		vo.setRows(result);		
		vo.setTotal(paramLists.getTotal());
		return TaotaoResult.ok(vo);
	}
	
	@Override
	public TaotaoResult listItemParamByItemCatId(Long id) {
		TbItemParamCriteria example = new TbItemParamCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(id);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}
	
	@Override
	public TaotaoResult saveItemParamByItemCatIdWithParamData(Long itemCatId, String paramData) {
		TbItemParam param = new TbItemParam();
		param.setItemCatId(itemCatId);
		param.setCreated(new Date());
		param.setUpdated(new Date());
		param.setParamData(paramData);
		itemParamMapper.insert(param);
		return TaotaoResult.ok();
	}
}

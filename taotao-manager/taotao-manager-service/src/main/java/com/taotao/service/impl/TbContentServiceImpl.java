package com.taotao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.DataGridDataVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCriteria;
import com.taotao.pojo.TbContentCriteria.Criteria;
import com.taotao.service.ITbContentService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description
 */
@Service
public class TbContentServiceImpl implements ITbContentService {

	@Autowired
	private TbContentMapper tbContentMapper;

	@Value("${rest.url}")
	private String restUrl;

	@Value("${get.syncIndexContentCategory}")
	private String getRequestSyncIndexContentCategory;

	@Override
	public DataGridDataVO pageContentByCategoryId(Long categoryId, Integer page, Integer rows) {
		TbContentCriteria example = new TbContentCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page, rows);
		List<TbContent> contents = tbContentMapper.selectByExample(example);
		PageInfo<TbContent> pageInfo = new PageInfo<>(contents);
		DataGridDataVO vo = new DataGridDataVO();
		vo.setTotal(pageInfo.getTotal());
		vo.setRows(contents);
		return vo;
	}

	@Override
	public TaotaoResult saveContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		tbContentMapper.insert(content);
		try {
			HttpUtil.doGet(restUrl + getRequestSyncIndexContentCategory + content.getCategoryId());
		} catch (Exception e) {
		}

		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteContents(List<Long> deleteIds) {
		deleteIds.forEach(id -> tbContentMapper.deleteByPrimaryKey(id));
		return TaotaoResult.ok();
	}
}

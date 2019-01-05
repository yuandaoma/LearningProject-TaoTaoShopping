package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNodeVO;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryCriteria;
import com.taotao.pojo.TbContentCategoryCriteria.Criteria;
import com.taotao.service.ITbContentCategoryService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description 内容类别管理service
 */
@Service
public class TbContentCategoryServiceImpl implements ITbContentCategoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<TreeNodeVO> listContentCategoryByParentId(Long parentId) {
		TbContentCategoryCriteria example = new TbContentCategoryCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> contentCategories = tbContentCategoryMapper.selectByExample(example);
		List<TreeNodeVO> retVos = new ArrayList<>();
		contentCategories.forEach(content -> {
			TreeNodeVO vo = new TreeNodeVO();
			vo.setId(content.getId());
			vo.setText(content.getName());
			vo.setState(content.getIsParent() ? "closed" : "open");
			retVos.add(vo);
		});
		return retVos;
	}

	@Override
	public TaotaoResult createContentCategory(Long parentId, String name) {
		// 添加一个节点
		TbContentCategory newCategory = new TbContentCategory();
		newCategory.setParentId(parentId);
		newCategory.setName(name);
		newCategory.setCreated(new Date());
		newCategory.setUpdated(new Date());
		// 状态。可选值:1(正常),2(删除)
		newCategory.setStatus(1);
		newCategory.setSortOrder(1);
		newCategory.setIsParent(false);
		tbContentCategoryMapper.insert(newCategory);
		// 获取其父节点，更改父节点的状态
		TbContentCategory parentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentCategory.getIsParent()) {
			parentCategory.setIsParent(true);
			tbContentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
		return TaotaoResult.ok(newCategory);
	}

	@Override
	public TaotaoResult deleteContentCategory(Long parentId, Long id) {
		if (parentId == 0) { // 说明删除的是子节点
			tbContentCategoryMapper.deleteByPrimaryKey(id);
		} else { // 说明删除的是祖先节点
			tbContentCategoryMapper.deleteByPrimaryKey(id);
			TbContentCategoryCriteria example = new TbContentCategoryCriteria();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(id);
			tbContentCategoryMapper.deleteByExample(example);
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateContentCategoryNameById(Long id, String name) {
		TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(id);
		category.setName(name);
		tbContentCategoryMapper.updateByPrimaryKey(category);
		return TaotaoResult.ok();
	}

}

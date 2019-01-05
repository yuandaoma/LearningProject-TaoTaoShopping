package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNodeVO;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description 
 */
public interface ITbContentCategoryService {
	
	/*
	 * 获取内容分类管理列表
	 */
	List<TreeNodeVO> listContentCategoryByParentId(Long parentId);

	/**
	 * 创建一个新的节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	TaotaoResult createContentCategory(Long parentId, String name);

	/**
	 * 删除一个节点
	 * @param parentId
	 * @param id
	 * @return
	 */
	TaotaoResult deleteContentCategory(Long parentId, Long id);

	/**
	 * 更新节点名称
	 * @param id
	 * @param name
	 * @return
	 */
	TaotaoResult updateContentCategoryNameById(Long id, String name);

}

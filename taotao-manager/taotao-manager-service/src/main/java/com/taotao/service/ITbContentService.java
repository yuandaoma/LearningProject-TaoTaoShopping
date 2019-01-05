package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.DataGridDataVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description 
 */
public interface ITbContentService {

	/**
	 * 分页展示内容
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	DataGridDataVO pageContentByCategoryId(Long categoryId, Integer page, Integer rows);

	/**
	 * 保存内容
	 * @param content
	 * @return
	 */
	TaotaoResult saveContent(TbContent content);

	/**
	 * 删除内容
	 * @param deleteIds
	 * @return
	 */
	TaotaoResult deleteContents(List<Long> deleteIds);

}

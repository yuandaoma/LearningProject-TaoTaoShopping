package com.edu.taotao.rest.service;

import java.util.List;

import com.taotao.pojo.TbContent;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description 
 */
public interface IContentService {

	/**
	 * 依据类别id获取分类
	 * @param id
	 * @return
	 */
	List<TbContent> listContentsByCategoryId(Long id);

}

package com.taotao.service;

import java.util.List;

import com.taotao.pojo.TbItemCat;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月1日
 * @description 商品种类Service
 */
public interface ITbItemCatService {
	
	/**
	 * 获取商品分类id
	 * @param parentId
	 * @return
	 * @throws Exception 
	 */
	List<TbItemCat> findItemCatListByParentId(Long parentId) throws Exception;

}

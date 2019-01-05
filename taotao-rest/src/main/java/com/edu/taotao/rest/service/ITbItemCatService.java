package com.edu.taotao.rest.service;

import com.edu.taotao.rest.vo.ItemCatVO;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月3日
 * @description 
 */
public interface ITbItemCatService {

	/**
	 * 获取商品分类，父类及其子类
	 * @return
	 */
	ItemCatVO listItemCat();

}

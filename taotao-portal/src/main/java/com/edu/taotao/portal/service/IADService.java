package com.edu.taotao.portal.service;

import java.util.List;

import com.taotao.common.pojo.ADItemVO;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description 
 */
public interface IADService {

	/**
	 * 获取首页广告位的展示信息
	 * @return
	 */
	List<ADItemVO> listAdItems();

}

package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.DataGridDataVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月1日
 * @description
 */
public interface ITbItemService {

	TbItem findOne(Long id) throws Exception;

	/**
	 * 查询展示列表
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	DataGridDataVO listItem(Integer page, Integer rows);

	/**
	 * 保存商品
	 * @param item
	 * @param itemParams 
	 * @param desc 
	 * @return
	 */
	TaotaoResult saveItem(TbItem item, String desc, String itemParams);

	/**
	 * 删除商品
	 * @param ids
	 * @return
	 */
	TaotaoResult deleteItems(List<Long> ids);


}

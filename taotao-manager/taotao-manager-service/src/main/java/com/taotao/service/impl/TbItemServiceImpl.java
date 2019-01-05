package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Data;
import com.taotao.common.pojo.DataGridDataVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCriteria;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ITbItemService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月1日
 * @description
 */
@Service
public class TbItemServiceImpl implements ITbItemService {

	@Autowired
	private TbItemMapper tbItemMapper;

	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public TbItem findOne(Long id) throws Exception {
		return tbItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public DataGridDataVO listItem(Integer page, Integer rows) {
		TbItemCriteria example = new TbItemCriteria();
		PageHelper.startPage(page, rows);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		DataGridDataVO vo = new DataGridDataVO();
		vo.setTotal(pageInfo.getTotal());
		vo.setRows(list);
		return vo;
	}

	@Override
	public TaotaoResult saveItem(TbItem item, String desc, String itemParams) {
		long itemId = generateTbItemId();
		item.setId(itemId);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		item.setStatus((byte) 1);
		tbItemMapper.insert(item);
		saveItemDesc(itemId, desc);
		saveItemParams(itemId, itemParams);
		return TaotaoResult.ok();
	}

	private void saveItemParams(Long itemId, String itemParams) {
		TbItemParamItem paramItem = new TbItemParamItem();
		paramItem.setCreated(new Date());
		paramItem.setUpdated(new Date());
		paramItem.setItemId(itemId);
		paramItem.setParamData(itemParams);
		tbItemParamItemMapper.insert(paramItem);
	}

	private void saveItemDesc(long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		tbItemDescMapper.insert(itemDesc);
	}

	private long generateTbItemId() {
		// 取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		// long millis = System.nanoTime();
		// 加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		// 如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		return Long.parseLong(str);
	}

	@Override
	public TaotaoResult deleteItems(List<Long> ids) {
		ids.forEach(id -> tbItemMapper.deleteByPrimaryKey(id));
		return TaotaoResult.ok();
	}

}

package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.taotao.common.pojo.ItemParamItemVO;
import com.taotao.common.utils.GsonUtil;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemCriteria;
import com.taotao.pojo.TbItemParamItemCriteria.Criteria;
import com.taotao.service.ITbItemParamItemService;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月3日
 * @description
 */
@Service
public class TbItemParamItemServiceImpl implements ITbItemParamItemService {

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public String findItemParamByItemId(Long itemId) {
		TbItemParamItemCriteria example = new TbItemParamItemCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.size() == 0) {
			return "";
		}
		String paramData = list.get(0).getParamData();
		List<ItemParamItemVO> vos = GsonUtil.getGson().fromJson(paramData, new TypeToken<List<ItemParamItemVO>>() {
		}.getType());
		StringBuffer sb = new StringBuffer();
		vos.forEach(vo -> {
			sb.append("<table cellpadding=\"0\" cellpadding=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
			sb.append("	<tbody>\n");
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + vo.getGroup() + "</th>\n");
			sb.append("		</tr>\n");
			vo.getParams().forEach(param -> {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">" + param.getK() + "</td>\n");
				sb.append("			<td>" + param.getV() + "</td>\n");
				sb.append("		</tr>\n");
			});
			sb.append("	</tbody>\n");
			sb.append("</table>");
		});
		return sb.toString();
	}

}

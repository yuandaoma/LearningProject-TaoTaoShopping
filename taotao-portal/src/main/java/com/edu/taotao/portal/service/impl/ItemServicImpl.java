package com.edu.taotao.portal.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edu.taotao.portal.service.IItemService;
import com.google.gson.reflect.TypeToken;
import com.taotao.common.pojo.ItemParamItemVO;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.GsonUtil;
import com.taotao.common.utils.HttpUtil;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月7日
 * @description
 */
@Service
public class ItemServicImpl implements IItemService {

	@Value("${rest.url}")
	private String restUrl;
	@Value("${restApi.itemInfo}")
	private String restApiItemInfo;
	@Value("${restApi.itemDesc}")
	private String restApiItemDesc;
	@Value("${restApi.itemParam}")
	private String restApiItemParam;

	@Override
	public TbItem findItemById(Long itemId) {
		String retJson = HttpUtil.doGet(restUrl + restApiItemInfo + itemId);
		if (StringUtils.isNotBlank(retJson)) {
			TaotaoResult retTaotao = GsonUtil.getGson().fromJson(retJson, TaotaoResult.class);
			if (retTaotao.getStatus() == 200) {
				String retItemJsonData = GsonUtil.getGson().toJson(retTaotao.getData());
				TbItem retItem = GsonUtil.getGson().fromJson(retItemJsonData, TbItem.class);
				return retItem;
			}
		}
		return null;
	}

	@Override
	public TbItemDesc findItemDescById(Long itemId) {
		String retJson = HttpUtil.doGet(restUrl + restApiItemDesc + itemId);
		if (StringUtils.isNotBlank(retJson)) {
			TaotaoResult retTaotao = GsonUtil.getGson().fromJson(retJson, TaotaoResult.class);
			if (retTaotao.getStatus() == 200) {
				String retItemJsonData = GsonUtil.getGson().toJson(retTaotao.getData());
				TbItemDesc itemDesc = GsonUtil.getGson().fromJson(retItemJsonData, TbItemDesc.class);
				return itemDesc;
			}
		}
		return null;
	}

	@Override
	public String findItemParamItemByItemId(Long itemId) {
		String retJson = HttpUtil.doGet(restUrl + restApiItemParam + itemId);
		if (StringUtils.isNotBlank(retJson)) {
			TaotaoResult retTaotao = GsonUtil.getGson().fromJson(retJson, TaotaoResult.class);
			if (retTaotao.getStatus() == 200) {
				String retItemParamItemJson = GsonUtil.getGson().toJson(retTaotao.getData());
				TbItemParamItem itemParamItem = GsonUtil.getGson().fromJson(retItemParamItemJson, TbItemParamItem.class);
				List<ItemParamItemVO> vos = GsonUtil.getGson().fromJson(itemParamItem.getParamData(),
						new TypeToken<List<ItemParamItemVO>>() {
						}.getType());

				StringBuffer sb = new StringBuffer();
				vos.forEach(vo -> {
					sb.append(
							"<table cellpadding=\"0\" cellpadding=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
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
		return null;
	}

}

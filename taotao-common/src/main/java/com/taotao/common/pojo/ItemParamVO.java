package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月3日
 * @description
 */
public class ItemParamVO implements Serializable{
	
	private static final long serialVersionUID = -346580223634308972L;

	private Long id;

	private Long itemCatId;

	private String itemCatName;

	private Date created;

	private Date updated;

	private String paramData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemCatId() {
		return itemCatId;
	}

	public void setItemCatId(Long itemCatId) {
		this.itemCatId = itemCatId;
	}

	public String getItemCatName() {
		return itemCatName;
	}

	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData;
	}

	@Override
	public String toString() {
		return "ItemParamVO [id=" + id + ", itemCatId=" + itemCatId + ", itemCatName=" + itemCatName + ", created="
				+ created + ", updated=" + updated + ", paramData=" + paramData + "]";
	}
	
	
}

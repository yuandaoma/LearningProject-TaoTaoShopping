package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月3日
 * @description
 */
public class ItemParamItemVO implements Serializable {

	private static final long serialVersionUID = 2928077269248978312L;

	private String group;

	private List<Param> params;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}

	public static class Param {
		private String k;
		private Object v;

		public String getK() {
			return k;
		}

		public void setK(String k) {
			this.k = k;
		}

		public Object getV() {
			return v;
		}

		public void setV(Object v) {
			this.v = v;
		}

	}

	@Override
	public String toString() {
		return "ItemParamItemVO [group=" + group + ", params=" + params + "]";
	}

}

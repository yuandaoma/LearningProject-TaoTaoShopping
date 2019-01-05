package com.edu.taotao.rest.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月3日
 * @description
 */
public class ItemCatVO {

	private List<Object> data;

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public static class ItemCat {

		@SerializedName("u")
		private String url;
		@SerializedName("n")
		private String name;
		@SerializedName("i")
		private List<Object> items;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Object> getItems() {
			return items;
		}

		public void setItems(List<Object> items) {
			this.items = items;
		}

	}

}

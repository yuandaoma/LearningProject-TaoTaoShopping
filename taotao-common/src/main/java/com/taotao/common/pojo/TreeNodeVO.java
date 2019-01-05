package com.taotao.common.pojo;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月1日
 * @description
 */
public class TreeNodeVO {

	// 编号
	private Long id;
	// 节点内容
	private String text;
	// 是否是父节点，close是，open子节点
	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}

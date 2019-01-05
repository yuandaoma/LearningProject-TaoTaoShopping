package com.taotao.common.pojo;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月10日
 * @description 购物车展示信息对象
 */
public class ItemCartVO {

	private Long id;
	private String title;
	private Integer num;
	private long price;
	private String image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}

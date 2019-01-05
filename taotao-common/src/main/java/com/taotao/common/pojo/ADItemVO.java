package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月4日
 * @description 广告位展示属性
 * 
 [
    {
        "srcB": "http://image.taotao.com/images/2015/03/03/2015030304360302109345.jpg",
        "height": 240,
        "alt": "",
        "width": 670,
        "src": "http://image.taotao.com/images/2015/03/03/2015030304360302109345.jpg",
        "widthB": 550,
        "href": "http://sale.jd.com/act/e0FMkuDhJz35CNt.html?cpdad=1DLSUE",
        "heightB": 240
    }
]
 */
public class ADItemVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -555938912335050276L;
	
	private String src;
	private Integer height;
	private Integer width;
	
	private String srcB;
	private Integer heightB;
	private Integer widthB;
	

	private String href;
	private String alt;
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public String getSrcB() {
		return srcB;
	}
	public void setSrcB(String srcB) {
		this.srcB = srcB;
	}
	public Integer getHeightB() {
		return heightB;
	}
	public void setHeightB(Integer heightB) {
		this.heightB = heightB;
	}
	public Integer getWidthB() {
		return widthB;
	}
	public void setWidthB(Integer widthB) {
		this.widthB = widthB;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

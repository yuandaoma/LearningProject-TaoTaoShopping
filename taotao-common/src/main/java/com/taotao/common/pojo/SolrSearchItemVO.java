package com.taotao.common.pojo;

import java.util.List;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月6日
 * @description solr查询商品信息
 */
public class SolrSearchItemVO {

	// 商品列表
	private List<SearchItemVO> itemList;

	// 总记录数
	private Long recordCount;

	// 总页数
	private Long pageCount;

	// 当前页
	private Long curPage;

	public List<SearchItemVO> getItemList() {
		return itemList;
	}

	public void setItemList(List<SearchItemVO> itemList) {
		this.itemList = itemList;
	}

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public Long getPageCount() {
		return pageCount;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

	public Long getCurPage() {
		return curPage;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

	@Override
	public String toString() {
		return "SolrSearchItemVO [itemList=" + itemList + ", recordCount=" + recordCount + ", pageCount=" + pageCount
				+ ", curPage=" + curPage + "]";
	}

}

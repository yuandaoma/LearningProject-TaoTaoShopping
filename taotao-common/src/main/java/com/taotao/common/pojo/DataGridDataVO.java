package com.taotao.common.pojo;

import java.util.List;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月1日
 * @description
 */
public class DataGridDataVO {

	private long total;
	private List<?> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}

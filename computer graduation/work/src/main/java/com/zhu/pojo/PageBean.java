package com.zhu.pojo;

import java.util.List;

/**
 * @author hzhu40
 *分页查询用的
 */
public class PageBean<T> {
	
	//总记录数
	private int totalCount;
	//页面数据
	private List<T> rowsBrands;
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getRowsBrands() {
		return rowsBrands;
	}
	public void setRowsBrands(List<T> rowsBrands) {
		this.rowsBrands = rowsBrands;
	}

}

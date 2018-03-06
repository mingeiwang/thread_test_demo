package com.commons.dao;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Page<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 页码，从1开始
	 */
	private int pageNumber;
	/**
	 * 页数
	 */
	private int pageSize;
	/**
	 * 返回的数据
	 */
	private List<T> list;
	/**
	 * 排序方式
	 */
	private String orderBy;
	/**
	 * 总条数
	 */
	private int totalCount;
	
	public Page(int pageNumber, int pageSize, int totalCount) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}
	
	public Page(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	public Page() {
		super();
	}

	/**
	 * 总页数。
	 * @return
	 */
	public long totalPage(){
		return this.totalCount % this.pageSize == 0
				? this.totalCount / this.pageSize
				: this.totalCount / this.pageSize + 1;
	}
	/**
	 * 第一条的位置
	 * @return
	 */
	public int firstResult(){
		return (pageNumber-1)*pageSize;
	}	
}

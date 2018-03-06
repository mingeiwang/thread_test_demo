package com.commons.beans;

import java.io.Serializable;
import java.util.List;

import com.commons.dao.Page;

import lombok.Data;

@Data
public class ResultBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int NO_LOGIN = -1;

	public static final int SUCCESS = 0;

	public static final int FAIL = 1;

	public static final int NO_PERMISSION = 2;

	private String msg = "success";

	private int code = SUCCESS;

	/**
	 * 分页：总条数
	 */
	private long totalCount;
	/**
	 * 分页数据
	 */
	private List listData;
	/**
	 * 分页：总页数
	 */
	private long totalPage;
	/**
	 * 分页：每页条数
	 */
	private int pageSize;
	/**
	 * 分页：页数
	 */
	private int pageNumber;
	
	private T data;

	public ResultBean() {
		super();
	}

	public ResultBean(T data) {
		super();
		setData(data);
	}

	public ResultBean(Throwable e) {
		super();
		this.msg = e.toString();
		this.code = FAIL;
	}
	
	public void setData(T data){
		if(data instanceof Page){
			Page page = (Page) data;
			this.totalCount = page.getTotalCount();
			this.totalPage = page.totalPage();
			this.listData = page.getList();
			this.pageNumber = page.getPageNumber();
			this.pageSize = page.getPageSize();
		}
		this.data = data;
	}
}

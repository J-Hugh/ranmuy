package com.neo.utils.values;

import java.util.List;

/**
 * BootstrapTable 分页返回实体
 * @author Neo
 *
 */
public class BootstrapTable<T> {

	/**
	 * 数据总量
	 */
	private Integer total;
	
	/**
	 * 数据集
	 */
	private List<T> rows;

	/**
	 * 构造函数
	 * @param total 数据总量
	 * @param rows 数据集
	 */
	public BootstrapTable(Integer total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}

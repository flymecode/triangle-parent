package com.xupt.common.entity;

import lombok.Data;

import java.util.List;

/**
 *
 * @author maxu
 */
@Data
public class PageResult<T> {


	private Long total;
	private List<T> rows;

	public PageResult() {

	}

	public PageResult(Long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

}

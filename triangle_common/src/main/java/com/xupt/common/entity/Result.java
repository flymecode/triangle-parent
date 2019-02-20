package com.xupt.common.entity;

import lombok.Data;

/**
 * @author maxu
 */
@Data
public class Result<T> {
	private boolean flag;//是否成功
	private Integer code;// 返回码
	private String message;//返回信息
	private T data;// 返回数据


	public Result() {
	}

	public Result(boolean flag, Integer code, String message, T
			data) {
		super();
		this.flag = flag;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Result(boolean flag, Integer code, String message) {
		super();
		this.flag = flag;
		this.code = code;
		this.message = message;
	}
}

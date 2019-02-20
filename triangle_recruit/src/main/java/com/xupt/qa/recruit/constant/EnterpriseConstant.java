package com.xupt.qa.recruit.constant;

import lombok.Getter;

/**
 * @author maxu
 */
@Getter
public enum  EnterpriseConstant {
	HOT("热门", 1);

	private String msg;
	private Integer code;

	EnterpriseConstant(String msg, Integer code) {
		this.msg = msg;
		this.code = code;
	}
}

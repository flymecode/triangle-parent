package com.xupt.base.controller;

import com.xupt.common.entity.Result;
import com.xupt.common.entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author maxu
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionController {

	@ExceptionHandler(value = Exception.class)
	public Result exception(Exception e) {
		log.error("出现异常【{}】",e.getMessage());
		return new Result<>(false, StatusCode.ERROR, e.getMessage());
	}
}

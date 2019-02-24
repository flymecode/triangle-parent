package com.xupt.qa.client;

import com.xupt.common.entity.Result;
import com.xupt.qa.client.impl.BaseClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author maxu
 */
@FeignClient(value = "base",fallback = BaseClientImpl.class)
public interface BaseClient {

	@RequestMapping(value="/label/{id}", method = RequestMethod.GET)
	Result findById(@PathVariable("id") String id);

}

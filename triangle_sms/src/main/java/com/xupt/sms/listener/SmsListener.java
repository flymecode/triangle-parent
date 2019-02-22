package com.xupt.sms.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author maxu
 */
@Slf4j
@Component
@RabbitListener(queues = "sms")
class SmsListener {

	@RabbitHandler
	public void executeSms(Map<String, String> map) {
		log.info(map.get("checkCode"));
	}
}

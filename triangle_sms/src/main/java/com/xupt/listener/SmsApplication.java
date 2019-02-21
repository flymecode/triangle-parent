package com.xupt.sms.listener;

/**
 * @author maxu
 */
@Commonent
@RabbitListener("sms")
@Slfj
public class SmsListener {

	@RabbitHandler
	public void executeSms(Map<String, String> map) {

	}
}

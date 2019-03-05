package ru.sent_notifications_service.services.factory.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.sent_notifications_service.services.factory.SentFactory;

public class SentByPhone implements SentFactory{
	
	private static Logger logger = LoggerFactory.getLogger(SentByPhone.class);

	@Override
	public void SentInformation(String data, String status) {
		StringBuilder sb = new StringBuilder();
		sb.append("На телефон ").append(data).append(" отправлено уведомление. Новый статус: ").append(status);
		logger.info(sb.toString());
	}
	
	@Override
	public String GetSentType() {
		return "Телефон";
	}

}

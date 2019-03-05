package ru.sent_notifications_service.services;

import org.springframework.stereotype.Service;

import ru.sent_notifications_service.services.factory.SentFactory;
import ru.sent_notifications_service.services.factory.implementation.SentByMail;
import ru.sent_notifications_service.services.factory.implementation.SentByPhone;

@Service
public class SentInformation {
	
	private SentFactory sentFactory;
	
	public String sentInformationToUser(String type, String data, String status) {
		if(type.toLowerCase().equals("phone")) {
			sentFactory = new SentByPhone();
		}else if(type.toLowerCase().equals("mail")) {
			sentFactory = new SentByMail();
		}
		if(sentFactory != null) {
			sentFactory.SentInformation(data,status);
			return "Сообщение успешно отправлено на " + sentFactory.GetSentType();
		}
		return "Ошибка отправки сообщения на - " + type;
	}

}

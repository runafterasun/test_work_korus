package ru.sent_notifications_service.services.factory;

public interface SentFactory {
	
	void SentInformation(String data, String status);
	
	String GetSentType();

}

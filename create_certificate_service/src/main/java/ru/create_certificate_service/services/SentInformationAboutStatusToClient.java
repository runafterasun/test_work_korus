package ru.create_certificate_service.services;

import ru.create_certificate_service.enums.SentTypes;

public interface SentInformationAboutStatusToClient {
	
	String sentInformation(SentTypes sentType, String data, String status);

}

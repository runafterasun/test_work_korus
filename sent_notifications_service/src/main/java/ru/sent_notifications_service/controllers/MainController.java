package ru.sent_notifications_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.sent_notifications_service.services.SentInformation;

@RestController
public class MainController {
	
	private SentInformation sent;
	
	@Autowired
	public MainController(SentInformation sent) {
		this.sent = sent;
	}

	/**Ответ предназначен для предположительного логирования*/
	@PostMapping(value = "/sentInformation/{sentType}/{data}/{status}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sentInformation(@PathVariable("sentType") String sentType,
												  @PathVariable("data") String data,
												  @PathVariable("status") String status){
		return new ResponseEntity<String>(sent.sentInformationToUser(sentType, data, status), HttpStatus.OK);		
	}

}

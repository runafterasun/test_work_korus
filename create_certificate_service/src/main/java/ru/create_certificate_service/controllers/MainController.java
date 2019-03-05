package ru.create_certificate_service.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.create_certificate_service.entity.UserSertificat;
import ru.create_certificate_service.enums.ApplicationStatus;
import ru.create_certificate_service.repository.UserSertificatRepository;

@RestController
public class MainController {
	
	private UserSertificatRepository userRepository;
	  
	@Autowired
	public MainController(UserSertificatRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping(value = "/getModelForGeneration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sent(@RequestBody UserSertificat userSertificat){
		userSertificat.setStatus(ApplicationStatus.START.getCode());
		userSertificat.setSentdate(new Date());
		return new ResponseEntity<>(userRepository.save(userSertificat).getId().toString(),HttpStatus.OK);	
	}
	
	@GetMapping(value = "/getStatus/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sentStatus(@PathVariable("id") String id){
		if(userRepository.findById(Long.parseLong(id)).isPresent()) {
			return new ResponseEntity<>(userRepository.findById(Long.parseLong(id)).get().getStatus(),HttpStatus.OK);	
		}else {
			return new ResponseEntity<>("Пользователя с запращиваемым id не существует",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/getSertificate/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sentSertificate(@PathVariable("id") String id){	
		if(userRepository.findById(Long.parseLong(id)).isPresent()) {
			if(userRepository.findByIdAndStatus(Long.parseLong(id),ApplicationStatus.COMPLETE.getCode()).isPresent()) {
				return new ResponseEntity<>(userRepository.findByIdAndStatus(Long.parseLong(id),ApplicationStatus.COMPLETE.getCode()).orNull().getSertificate() ,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Сертификат с таким идентификатором еще не обработан и находится на стадии: "+userRepository.findById(Long.parseLong(id)).get().getStatus(),HttpStatus.OK);	
			}
		}else {
			return new ResponseEntity<>("Пользователя с запращиваемым id не существует",HttpStatus.BAD_REQUEST);
		}	
	}
}

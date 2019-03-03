package ru.korus_certification_service.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.korus_certification_service.DTO.CertificationDTO;
import ru.korus_certification_service.service.CertificateGenerationModule;

@RestController
public class MainController {
	   
    private Validator sertificationDTOValidator;
	private CertificateGenerationModule certificateModule;	
	
	@Autowired
    public MainController(@Qualifier("SertificationDTOValidator") Validator sertificationDTOValidator, CertificateGenerationModule certificateModule) {
		this.sertificationDTOValidator = sertificationDTOValidator;
		this.certificateModule = certificateModule;
	}

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(sertificationDTOValidator);
    }

	@PostMapping(value = "/sentInformation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> sent(@Valid @RequestBody CertificationDTO certificationDTO, Errors error) throws IOException{
		if(error.hasErrors()) {
			return new ResponseEntity<>(error.getAllErrors().stream().map(mapper -> mapper.getDefaultMessage()).collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Заявка принята. Идентификатор заявки: " + certificateModule.sentModelForGeneration(certificationDTO),HttpStatus.OK);	
	}
	
	@GetMapping(value = "/getStatus/{id}")
	public ResponseEntity<String> sentStatus(@PathVariable("id") String id){
		return new ResponseEntity<>(certificateModule.getGenerationStatus(Long.parseLong(id)),HttpStatus.OK);	
	}
	
	@GetMapping(value = "/getSertificate/{id}")
	public ResponseEntity<String> sentSertificate(@PathVariable("id") String id){	
		return new ResponseEntity<>(certificateModule.getCompleteCertificate(Long.parseLong(id)),HttpStatus.OK);	
	}
	
	
}

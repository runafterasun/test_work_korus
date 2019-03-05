package ru.korus_certification_service.service.implementation;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ru.korus_certification_service.DTO.CertificationDTO;
import ru.korus_certification_service.service.CertificateGenerationModule;

@Service
public class CertificateGenerationModuleImplementation implements CertificateGenerationModule {

	@Override
	public String sentModelForGeneration(CertificationDTO dto) throws IOException {		
        HttpEntity<CertificationDTO> entity = new HttpEntity<>(dto,defaultHttpHeaders());          
        ResponseEntity<String> answer = new RestTemplate().postForEntity("http://localhost:8083/getModelForGeneration", entity, String.class);
        	return answer.getBody();
	}

	@Override
	public String getGenerationStatus(Long id) {
		HttpEntity<CertificationDTO> entity = new HttpEntity<>(defaultHttpHeaders());          
        ResponseEntity<String> answer = new RestTemplate().getForEntity("http://localhost:8083/getStatus/"+id, String.class, entity);
        if(answer.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
        	return "Случилась критическая ошибка на сервере обработки сертификатов.";
        }else {
        	return answer.getBody();
        }
		
	}

	@Override
	public String getCompleteCertificate(Long id) {
		HttpEntity<CertificationDTO> entity = new HttpEntity<>(defaultHttpHeaders());          
        ResponseEntity<String> answer = new RestTemplate().getForEntity("http://localhost:8083/getSertificate/"+id, String.class, entity);
        if(answer.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
        	return "Случилась критическая ошибка на сервере обработки сертификатов.";
        }else {
        	return answer.getBody();
        }
	}
	
	private HttpHeaders defaultHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        headers.set("Content-type", "application/json");
        return headers;
	}

}

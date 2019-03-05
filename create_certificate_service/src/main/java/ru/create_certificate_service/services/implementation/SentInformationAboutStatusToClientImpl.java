package ru.create_certificate_service.services.implementation;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ru.create_certificate_service.enums.SentTypes;
import ru.create_certificate_service.services.SentInformationAboutStatusToClient;

@Service
public class SentInformationAboutStatusToClientImpl implements SentInformationAboutStatusToClient {

	@Override
	public String sentInformation(SentTypes sentType, String data, String status) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://localhost:8085/sentInformation/").append(sentType).append("/").append(data).append("/").append(status);
		
		HttpEntity<String> entity = new HttpEntity<>(data,defaultHttpHeaders());          
        ResponseEntity<String> answer = new RestTemplate().postForEntity(sb.toString(), entity, String.class);
		return answer.getBody();
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

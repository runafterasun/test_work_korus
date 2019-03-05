package ru.create_certificate_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ru.create_certificate_service.repository.UserSertificatRepository;
import ru.create_certificate_service.services.SentInformationAboutStatusToClient;
import ru.create_certificate_service.services.SertificateCreator;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
    
    @Bean 
    public SertificateCreator sertificateCreator(@Autowired UserSertificatRepository userRepository,@Autowired SentInformationAboutStatusToClient sentToClient) {
    	return new SertificateCreator(userRepository,sentToClient);
    }
}

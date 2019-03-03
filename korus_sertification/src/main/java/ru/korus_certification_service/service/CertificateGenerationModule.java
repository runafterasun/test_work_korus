package ru.korus_certification_service.service;

import java.io.IOException;

import ru.korus_certification_service.DTO.CertificationDTO;

public interface CertificateGenerationModule {
	
	Long sentModelForGeneration(CertificationDTO dto) throws IOException;
	
	String getGenerationStatus(Long id);
	
	String getCompleteCertificate(Long id);

}

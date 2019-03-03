package ru.korus_certification_service.validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.korus_certification_service.DTO.CertificationDTO;

@Service("SertificationDTOValidator")
public class CertificationDTOValidator implements Validator{

	@Autowired
	private MessageSource ms;	
	private final String DEFAULT_MESSAGE = "Суммарная длина ФИО превышает допустимый размер";
	private final String RF_LENGTH = "40";
	private final String FOREIGN_LENGTH = "30";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CertificationDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {						
			CertificationDTO dto = (CertificationDTO) target;
			if(dto.getName() != null && dto.getSurname() != null && dto.getPetricname() != null && dto.isNationalFlag() != null) {
				if(dto.isNationalFlag() && dto.getName().length() + dto.getSurname().length() + dto.getPetricname().length() > 40) {
					errors.rejectValue("", "",ms.getMessage("main.ivalid.fio.lenght",new Object[]{RF_LENGTH}, DEFAULT_MESSAGE, Locale.getDefault()));
				}else if (!dto.isNationalFlag() && dto.getName().length() + dto.getSurname().length() > 30) {
					errors.rejectValue("", "",ms.getMessage("main.ivalid.fio.lenght",new Object[]{FOREIGN_LENGTH}, DEFAULT_MESSAGE, Locale.getDefault()));
				}
			}
	}

}

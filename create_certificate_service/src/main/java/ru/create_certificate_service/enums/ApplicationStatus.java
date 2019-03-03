package ru.create_certificate_service.enums;

public enum ApplicationStatus {
	START("Принята"),
	INPROCESS("В процессе обработки"),
	ERROR("Ошибка обработки"),
	COMPLETE("Готово");
	
	private String code;

	private ApplicationStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}

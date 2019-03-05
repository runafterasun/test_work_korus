package ru.korus_certification_service.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CertificationDTO {
	

	/**Суммарное значение полей name, surname и petricname
	 * При nationalFlag = true не должно привышать 40 символов.
	 * ________________________________________________________
	 * Суммарное значение полей name, surname
	 * При nationalFlag = false не должно привышать 30 символов*/
	@NotNull(message = "Имя должно быть заполнено")
	private String name;
	
	@NotNull(message = "Фамилия должна быть заполнена")
	private String surname;
	
	@NotNull(message = "Отчество должно быть заполнено")
	private String petricname;
	
	/**При указании значения 0 - является гражданином РФ, 1 - не являеться гражданином РФ*/
	@NotNull(message = "Не было указано гражданство")
	private Boolean nationalFlag;
	
	@Email(message = "Указан не верный электронный адрес")
	private String mail;
	
	private Long phone;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPetricname() {
		return petricname;
	}

	public void setPetricname(String petricname) {
		this.petricname = petricname;
	}

	public Boolean isNationalFlag() {
		return nationalFlag;
	}

	public void setNationalFlag(Boolean nationalFlag) {
		this.nationalFlag = nationalFlag;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Объект для дальнейшего трансера на сервис создания сертификата SertificationDTO [name=" + name + ", surname=" + surname + ", petricname=" + petricname
				+ ", nationalFlag=" + nationalFlag + ", mail=" + mail + ", phone=" + phone + "]";
	}
	
	

}

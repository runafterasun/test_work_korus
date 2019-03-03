package ru.create_certificate_service.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "USERSERTIFICAT")
public class UserSertificat {
	
	@Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(initialValue = 1, name = "id_generator")
    private Long id;
	
	@Column(nullable = false, name = "name")
	private String name;
	
	@Column(nullable = false, name = "surname")
	private String surname;
	
	@Column(nullable = false, name = "petricname")
	private String petricname;
	
	@Column(nullable = false, name = "nationalflag")
	private Boolean nationalFlag;
	
	@Column(name = "mail")
	private String mail;	
	
	@Column(name = "phone")
	private Long phone;
	
	@Column(name = "sentdate")
	private Date sentdate;
	
	@Column(name = "status", length = 30)
    private String status;
	
	@Column(name = "sertificate")
    private String sertificate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean getNationalFlag() {
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

	public Date getSentdate() {
		return sentdate;
	}

	public void setSentdate(Date sentdate) {
		this.sentdate = sentdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSertificate() {
		return sertificate;
	}

	public void setSertificate(String sertificate) {
		this.sertificate = sertificate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSertificat other = (UserSertificat) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Основной класс данных UserSertificat [id=" + id + ", name=" + name + ", surname=" + surname + ", petricname=" + petricname
				+ ", nationalFlag=" + nationalFlag + ", mail=" + mail + ", phone=" + phone + ", sentdate=" + sentdate
				+ ", status=" + status + ", sertificate=" + sertificate + "]";
	}
	
	

}

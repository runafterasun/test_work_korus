package ru.sent_notifications_service.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.sent_notifications_service.services.SentInformation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SentInformationTest {
	
	@Autowired
	public SentInformation sent;
	
	@Test
	public void testSentInformationByPhone() throws Exception {
	assertThat(sent.sentInformationToUser("phone", "79781234567", "Отправленно")).contains("Телефон");
	}
	
	@Test
	public void testSentInformationByMail() throws Exception {
	assertThat(sent.sentInformationToUser("mail", "test@test.ru", "Отправленно")).contains("Почту");
	}
	
	@Test
	public void testSentInformationByOther() throws Exception {
	assertThat(sent.sentInformationToUser("other", "test@test.ru", "Отправленно")).contains("Ошибка");
	}
}

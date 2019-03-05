package ru.create_certificate_service.services;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.create_certificate_service.entity.UserSertificat;
import ru.create_certificate_service.enums.ApplicationStatus;
import ru.create_certificate_service.enums.SentTypes;
import ru.create_certificate_service.repository.UserSertificatRepository;
import java.util.Base64;
import java.util.LinkedList;
import java.util.Optional;

@Component
public class SertificateCreator implements Runnable {

	@Autowired
	private UserSertificatRepository userRepository;
	private SentInformationAboutStatusToClient sentToClient;
	private Thread thread;

	public SertificateCreator(UserSertificatRepository userRepository, SentInformationAboutStatusToClient sentToClient) {
		this.sentToClient = sentToClient;
		this.userRepository = userRepository;
		thread = new Thread(this, "Поток обработки сертификата");
		thread.start();
	}

	@Override
	public void run() {
		createSertificat();
	}
	
	private void createSertificat() {
		try {
			Queue<UserSertificat> queue = new LinkedList<UserSertificat>(
					userRepository.findAllByStatusOrderByIdAsc(ApplicationStatus.START.getCode()));
			while (true) {
				if (queue.isEmpty()) {
					queue = new LinkedList<UserSertificat>(
							userRepository.findAllByStatusOrderByIdAsc(ApplicationStatus.START.getCode()));
					TimeUnit.MINUTES.sleep(1);
				} else if (!queue.isEmpty()) {
					UserSertificat us = queue.poll();
					us.setStatus(ApplicationStatus.INPROCESS.getCode());
					sentInformationToClient(us);
					userRepository.save(us);
					TimeUnit.SECONDS.sleep((int) (Math.random() * 100));
					if ((int)(Math.random() * 10) % 2 == 0) {
						us.setStatus(ApplicationStatus.ERROR.getCode());
						sentInformationToClient(us);
						userRepository.save(us);
					} else {
						us.setSertificate(new String(Base64.getEncoder().encode(appendForEncode(us).getBytes())));
						us.setStatus(ApplicationStatus.COMPLETE.getCode());
						sentInformationToClient(us);
						userRepository.save(us);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
		
	/**Не понял задачу. Делать рассылку на все или на первое что является не нулевым*/
	private void sentInformationToClient(UserSertificat us) {
		Optional.ofNullable(us.getPhone()).ifPresent(consumer ->  sentToClient.sentInformation(SentTypes.PHONE, consumer.toString(), us.getStatus()));
		Optional.ofNullable(us.getMail()).ifPresent(consumer ->  sentToClient.sentInformation(SentTypes.MAIL, consumer, us.getStatus()));
	}
	
	private String appendForEncode(UserSertificat us) {
		StringBuilder sb = new StringBuilder();
		sb.append(us.getName())
		.append(us.getSurname())
		.append(us.getPetricname())
		.append(us.getMail())
		.append(us.getPhone())
		.append(us.getSentdate());
		return sb.toString();
	}

}

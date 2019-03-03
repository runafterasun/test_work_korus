package ru.create_certificate_service.services;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.create_certificate_service.entity.UserSertificat;
import ru.create_certificate_service.enums.ApplicationStatus;
import ru.create_certificate_service.repository.UserSertificatRepository;
import java.util.Base64;
import java.util.LinkedList;

@Component
public class SertificateCreator implements Runnable {

	@Autowired
	private UserSertificatRepository userRepository;
	private Thread thread;

	public SertificateCreator(UserSertificatRepository userRepository) {
		this.userRepository = userRepository;
		thread = new Thread(this, "Поток обработки сертификата");
		thread.start();
	}

	@Override
	public void run() {
		try {
			Queue<UserSertificat> queue = new LinkedList<UserSertificat>(
					userRepository.findAllByStatusOrderByIdAsc(ApplicationStatus.START.getCode()));
			while (true) {
				if (queue.isEmpty()) {
					queue = new LinkedList<UserSertificat>(
							userRepository.findAllByStatusOrderByIdAsc(ApplicationStatus.START.getCode()));
					TimeUnit.MINUTES.sleep(1);
				} else if (!queue.isEmpty()) {
					TimeUnit.SECONDS.sleep((long) Math.random() * 100);
					StringBuilder sb = new StringBuilder();
					UserSertificat us = queue.poll();
					us.setStatus(ApplicationStatus.INPROCESS.getCode());
					userRepository.save(us);
					if (((int) Math.random() * 10000) % 2 == 0) {
						us.setStatus(ApplicationStatus.ERROR.getCode());
						userRepository.save(us);
					} else {
						sb.append(us.getName())
						.append(us.getSurname())
						.append(us.getPetricname())
						.append(us.getMail())
						.append(us.getPhone())
						.append(us.getSentdate());
						us.setSertificate(new String(Base64.getEncoder().encode(sb.toString().getBytes())));
						us.setStatus(ApplicationStatus.COMPLETE.getCode());
						userRepository.save(us);
						TimeUnit.SECONDS.sleep((long) Math.random() * 100);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

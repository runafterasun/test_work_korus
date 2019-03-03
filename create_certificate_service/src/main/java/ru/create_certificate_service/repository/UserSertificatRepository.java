package ru.create_certificate_service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.google.common.base.Optional;
import ru.create_certificate_service.entity.UserSertificat;

public interface UserSertificatRepository extends CrudRepository<UserSertificat, Long>{
	
	Optional<UserSertificat> findByIdAndStatus(Long id, String Status);
	
	List<UserSertificat> findAllByStatusOrderByIdAsc(String status);

}

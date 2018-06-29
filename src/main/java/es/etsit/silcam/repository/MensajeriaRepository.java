package es.etsit.silcam.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.Hilo;

@Repository("mensajeriaRepository")
public interface MensajeriaRepository extends MongoRepository<Hilo, String>{

	public Page<Hilo> findAll(Pageable pageable);
	
	public List<Hilo> findByIdExpediente(long idExpediente);
}

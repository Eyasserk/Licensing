package es.etsit.silcam.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.Notificacion;

@Repository("notificacionRepository")
public interface NotificacionRepository extends MongoRepository<Notificacion, String>{

	public List<Notificacion> findByIdExpediente(long idExpediente);
	
	public List<Notificacion> findByNumeroExpediente(String numeroExpediente);
	
	public List<Notificacion> findByIdPersona(long idPersona);
}

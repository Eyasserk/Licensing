package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.EstadoSolicitud;

@Repository("estadoSolicitudRepository")
public interface EstadoSolicitudRepository extends JpaRepository<EstadoSolicitud, Long>{
	
	public void deleteById(long id);

}

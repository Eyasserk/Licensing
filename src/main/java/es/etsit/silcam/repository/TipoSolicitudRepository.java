package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.TipoSolicitud;

@Repository("tipoSolicitudRepository")
public interface TipoSolicitudRepository extends JpaRepository<TipoSolicitud, Long>{

}

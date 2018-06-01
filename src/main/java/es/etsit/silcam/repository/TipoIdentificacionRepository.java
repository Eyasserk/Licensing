package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.TipoIdentificacion;

@Repository("tipoIdentificacionRepository")
public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Long>{

	public void deleteById(long id);
	
}

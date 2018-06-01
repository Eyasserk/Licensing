package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.TipoPersona;

@Repository("tipoPersonaRepository")
public interface TipoPersonaRepository extends JpaRepository<TipoPersona, Long>{

	public void deleteById(long id);
	
}

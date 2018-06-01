package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.Expediente;

@Repository("expedienteRepository")
public interface ExpedienteRepository extends JpaRepository<Expediente, Long>{
	
	public void deleteById(long id);
	
}


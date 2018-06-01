package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.FaseExpediente;

@Repository("faseExpedienteRepository")
public interface FaseExpedienteRepository extends JpaRepository<FaseExpediente, Long>{

	public void deleteById(long id);
	
}

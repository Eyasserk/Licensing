package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.Pais;

@Repository("paisRepository")
public interface PaisRepository extends JpaRepository<Pais, Long>{

	public void deleteById(long id);
	
}

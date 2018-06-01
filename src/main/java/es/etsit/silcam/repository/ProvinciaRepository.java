package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.Provincia;

@Repository("provinciaRepository")
public interface ProvinciaRepository extends JpaRepository<Provincia, Long>{

	public void deleteById(long id);
	
}

package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.Sexo;

@Repository("sexoRepository")
public interface SexoRepository extends JpaRepository<Sexo, Long>{

	public void deleteById(long id);
	
}

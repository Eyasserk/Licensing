package es.etsit.silcam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.PersonaFisica;

@Repository("personaFisicaRepository")
public interface PersonaFisicaRepository extends JpaRepository<PersonaFisica, Long>, JpaSpecificationExecutor<PersonaFisica>{
	
	public void deleteById(long id);
	
	public List<PersonaFisica> findByNumeroIdentificacionContaining(String pattern);
	
	@Query("select p from PersonaFisica p where lower(concat(p.nombre,concat(p.apellido1,p.apellido2))) like %:pattern%")
	public List<PersonaFisica> findByNombreCompletoStartingWith(@Param("pattern") String pattern);
	
}

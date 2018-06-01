package es.etsit.silcam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.PersonaJuridica;

@Repository("personaJuridicaRepository")
public interface PersonaJuridicaRepository extends JpaRepository<PersonaJuridica, Long>{
	
	public void deleteById(long id);
	
	public List<PersonaJuridica> findByNumeroIdentificacionContaining(String pattern);
	
	public List<PersonaJuridica> findByRazonSocialContaining(String pattern);

}

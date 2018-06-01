package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.filter.PersonaFisicaFilter;

public interface PersonaFisicaService {

	public List<PersonaFisica> findAll(PersonaFisicaFilter filter);
	
	public PersonaFisica findById(long id);
	
	public PersonaFisica create(PersonaFisica persona);
	
	public PersonaFisica update(PersonaFisica persona);
	
	public void delete(long id);
	
	public List<PersonaFisica> findByNumeroIdentificacionLike(String pattern);
	
	public List<PersonaFisica> findByNombreCompletoStartingWith(String pattern);
	
}

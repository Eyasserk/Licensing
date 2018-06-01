package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.PersonaJuridica;
import es.etsit.silcam.filter.PersonaJuridicaFilter;

public interface PersonaJuridicaService {
	
	public List<PersonaJuridica> findAll(PersonaJuridicaFilter filter);
	
	public PersonaJuridica findById(long id);
	
	public PersonaJuridica create(PersonaJuridica personaJuridica);
	
	public PersonaJuridica update(PersonaJuridica personaJuridica);
	
	public void delete(long id);
	
	public List<PersonaJuridica> findByNumeroIdentificacionLike(String pattern);
	
	public List<PersonaJuridica> findByRazonSocialLike(String pattern);
}

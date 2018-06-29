package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.PersonaJuridica;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.filter.PersonaJuridicaFilter;
import es.etsit.silcam.repository.PersonaJuridicaRepository;
import es.etsit.silcam.service.PersonaJuridicaService;

@Service("personaJuridicaService")
public class PersonaJuridicaServiceImpl implements PersonaJuridicaService{

	
	private PersonaJuridicaRepository personaJuridicaRepository;
	
	@Autowired
	public void setPersonaJuridicaRepository(PersonaJuridicaRepository personaJuridicaRepository) {
		this.personaJuridicaRepository = personaJuridicaRepository;
	}
	
	@Override
	public List<PersonaJuridica> findAll(PersonaJuridicaFilter filter) {
		if(filter == null) {
			return personaJuridicaRepository.findAll();
		}else {
			return personaJuridicaRepository.findAll(filter.getSpecifications(), filter.getPageRequest())
					.getContent();
		}
	}

	@Override
	public PersonaJuridica findById(long id) {
		PersonaJuridica persona = personaJuridicaRepository.getOne(id);
		if(persona == null) {
			throw new NotFoundException("Persona no encontrada");
		}
		return persona;
	}

	@Override
	public PersonaJuridica create(PersonaJuridica persona) {
		//TODO check conflicts and assign default values
		return personaJuridicaRepository.saveAndFlush(persona);
	}

	@Override
	public PersonaJuridica update(PersonaJuridica persona) {
		//TODO check null values before update
		return personaJuridicaRepository.saveAndFlush(persona);
	}

	@Override
	public void delete(long id) {
		PersonaJuridica persona = personaJuridicaRepository.getOne(id);
		if(persona ==null) {
			throw new NotFoundException("Persona no encontrada");
		}
		personaJuridicaRepository.deleteById(id);
	}

	@Override
	public List<PersonaJuridica> findByNumeroIdentificacionLike(String pattern) {
		return personaJuridicaRepository.findByNumeroIdentificacionContaining(pattern);
	}
	
	@Override
	public List<PersonaJuridica> findByRazonSocialLike(String pattern){
		return personaJuridicaRepository.findByRazonSocialContaining(pattern);
	}

}

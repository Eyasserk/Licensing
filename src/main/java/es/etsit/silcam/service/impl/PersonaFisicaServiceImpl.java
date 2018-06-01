package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.filter.PersonaFisicaFilter;
import es.etsit.silcam.repository.PersonaFisicaRepository;
import es.etsit.silcam.service.PersonaFisicaService;

@Service("personaFisicaService")
public class PersonaFisicaServiceImpl implements PersonaFisicaService{

	
	private PersonaFisicaRepository personaFisicaRepository;
	
	@Autowired
	public void setPersonaFisicaRepository(PersonaFisicaRepository personaFisicaRepository) {
		this.personaFisicaRepository = personaFisicaRepository;
	}
	
	@Override
	public List<PersonaFisica> findAll(PersonaFisicaFilter filter) {
		// TODO implement here the filter
		return personaFisicaRepository.findAll();
	}

	@Override
	public PersonaFisica findById(long id) {
		PersonaFisica persona = personaFisicaRepository.getOne(id);
		if(persona == null) {
			throw new NotFoundException("Persona no encontrada");
		}
		return persona;
	}

	@Override
	public PersonaFisica create(PersonaFisica persona) {
		//TODO check conflicts and assign default values
		return personaFisicaRepository.saveAndFlush(persona);
	}

	@Override
	public PersonaFisica update(PersonaFisica persona) {
		//TODO check null values before update
		return personaFisicaRepository.saveAndFlush(persona);
	}

	@Override
	public void delete(long id) {
		PersonaFisica persona = personaFisicaRepository.getOne(id);
		if(persona ==null) {
			throw new NotFoundException("Persona no encontrada");
		}
		personaFisicaRepository.deleteById(id);
	}

	@Override
	public List<PersonaFisica> findByNumeroIdentificacionLike(String pattern) {
		return personaFisicaRepository.findByNumeroIdentificacionContaining(pattern);
	}

	@Override
	public List<PersonaFisica> findByNombreCompletoStartingWith(String pattern) {
		return personaFisicaRepository.findByNombreCompletoStartingWith(pattern.toLowerCase());
	}

}

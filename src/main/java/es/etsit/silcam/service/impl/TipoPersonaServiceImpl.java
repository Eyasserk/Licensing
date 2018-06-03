package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.TipoPersona;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.repository.TipoPersonaRepository;
import es.etsit.silcam.service.TipoPersonaService;

@Service("tipoPersonaService")
public class TipoPersonaServiceImpl implements TipoPersonaService{

	
	private TipoPersonaRepository tipoPersonaRepository;
	
	@Autowired
	public void settipoPersonaRepository(TipoPersonaRepository tipoPersonaRepository) {
		this.tipoPersonaRepository = tipoPersonaRepository;
	}
	
	@Override
	public List<TipoPersona> findAll() {
		return tipoPersonaRepository.findAll();
	}

	@Override
	public TipoPersona findById(long id) {
		TipoPersona grupo = tipoPersonaRepository.getOne(id);
		if(grupo == null) {
			throw new NotFoundException("Tipo Persona no encontrado");
		}
		return grupo;
	}

	@Override
	public TipoPersona create(TipoPersona tipoPersona) {
		return tipoPersonaRepository.saveAndFlush(tipoPersona);
	}

	@Override
	public TipoPersona update(TipoPersona tipoPersona) {
		//TODO check null values
		return tipoPersonaRepository.saveAndFlush(tipoPersona);
	}

	@Override
	public void delete(long id) {
		TipoPersona grupo = tipoPersonaRepository.getOne(id);
		if(grupo == null) {
			throw new NotFoundException("Tipo Persona no encontrado");
		}
		tipoPersonaRepository.deleteById(id);
	}

}

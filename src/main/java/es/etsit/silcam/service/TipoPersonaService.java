package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.TipoPersona;

public interface TipoPersonaService {

	public List<TipoPersona> findAll();
	
	public TipoPersona findById(long id);
	
	public TipoPersona create(TipoPersona tipoPersona);
	
	public TipoPersona update(TipoPersona tipoPersona);
	
	public void delete(long id);
}

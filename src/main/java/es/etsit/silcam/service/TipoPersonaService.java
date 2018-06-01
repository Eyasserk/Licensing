package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.TipoPersona;
import es.etsit.silcam.filter.TipoPersonaFilter;

public interface TipoPersonaService {

	public List<TipoPersona> findAll(TipoPersonaFilter filter);
	
	public TipoPersona findById(long id);
	
	public TipoPersona create(TipoPersona tipoPersona);
	
	public TipoPersona update(TipoPersona tipoPersona);
	
	public void delete(long id);
}

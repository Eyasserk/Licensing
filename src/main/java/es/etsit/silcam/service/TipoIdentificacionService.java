package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.TipoIdentificacion;
import es.etsit.silcam.filter.TipoIdentificacionFilter;

public interface TipoIdentificacionService {

	public List<TipoIdentificacion> findAll(TipoIdentificacionFilter filter);
	
	public TipoIdentificacion findById(long id);
	
	public TipoIdentificacion create(TipoIdentificacion tipoIdentificacion);
	
	public TipoIdentificacion update(TipoIdentificacion tipoIdentificacion);
	
	public void delete(long id);
}

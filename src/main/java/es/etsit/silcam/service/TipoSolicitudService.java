package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.TipoSolicitud;

public interface TipoSolicitudService {

	
	public List<TipoSolicitud> findAll();
	
	public TipoSolicitud findById(long id);
	
}

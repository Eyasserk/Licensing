package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.EstadoSolicitud;

public interface EstadoSolicitudService {

	public List<EstadoSolicitud> findAll();
	
	public EstadoSolicitud findById(long id);
	
	public EstadoSolicitud create(EstadoSolicitud estadoSolicitud);
	
	public EstadoSolicitud update(EstadoSolicitud estadoSolicitud);
	
	public void delete(long id);
}

package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.EstadoSolicitud;
import es.etsit.silcam.filter.EstadoSolicitudFilter;

public interface EstadoSolicitudService {

	public List<EstadoSolicitud> findAll(EstadoSolicitudFilter filter);
	
	public EstadoSolicitud findById(long id);
	
	public EstadoSolicitud create(EstadoSolicitud estadoSolicitud);
	
	public EstadoSolicitud update(EstadoSolicitud estadoSolicitud);
	
	public void delete(long id);
}

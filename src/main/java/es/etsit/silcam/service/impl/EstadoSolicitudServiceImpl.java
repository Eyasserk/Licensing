package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.EstadoSolicitud;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.filter.EstadoSolicitudFilter;
import es.etsit.silcam.repository.EstadoSolicitudRepository;
import es.etsit.silcam.service.EstadoSolicitudService;

@Service("estadoSolicitudService")
public class EstadoSolicitudServiceImpl implements EstadoSolicitudService{

	
	private EstadoSolicitudRepository estadoSolicitudRepository;
	
	@Autowired
	public void setEstadoSolicitudRepository(EstadoSolicitudRepository estadoSolicitudRepository) {
		this.estadoSolicitudRepository = estadoSolicitudRepository;
	}
	
	@Override
	public List<EstadoSolicitud> findAll(EstadoSolicitudFilter filter) {
		// TODO implement here the filter
		return estadoSolicitudRepository.findAll();
	}

	@Override
	public EstadoSolicitud findById(long id) {
		EstadoSolicitud estado = estadoSolicitudRepository.getOne(id);
		if(estado == null) {
			throw new NotFoundException("Estado no encontrado");
		}
		return estado;
	}

	@Override
	public EstadoSolicitud create(EstadoSolicitud estadoSolicitud) {
		return estadoSolicitudRepository.saveAndFlush(estadoSolicitud);
	}

	@Override
	public EstadoSolicitud update(EstadoSolicitud estadoSolicitud) {
		return estadoSolicitudRepository.saveAndFlush(estadoSolicitud);
	}

	@Override
	public void delete(long id) {
		EstadoSolicitud estado = estadoSolicitudRepository.getOne(id);
		if(estado == null) {
			throw new NotFoundException("Estado no encontrado");
		}
		estadoSolicitudRepository.deleteById(id);
	}

}

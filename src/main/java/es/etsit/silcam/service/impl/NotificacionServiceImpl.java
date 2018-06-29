package es.etsit.silcam.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.Expediente;
import es.etsit.silcam.entity.Notificacion;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.repository.ExpedienteRepository;
import es.etsit.silcam.repository.NotificacionRepository;
import es.etsit.silcam.service.NotificacionService;

@Service("notificacionService")
public class NotificacionServiceImpl implements NotificacionService{

	private NotificacionRepository notificacionRepository;
	private ExpedienteRepository expedienteRepository;
	
	@Autowired
	public void setNotificacionRepository(NotificacionRepository notificacionRepository) {
		this.notificacionRepository = notificacionRepository;
	}
	
	@Autowired
	public void setExpedienteRepository(ExpedienteRepository expedienteRepository) {
		this.expedienteRepository = expedienteRepository;
	}
	
	@Override
	public List<Notificacion> findAll(int page, int size) {
		return notificacionRepository.findAll(PageRequest.of(page, size)).getContent();
	}
	
	@Override
	public List<Notificacion> findAllByIdPersona(long idPersona) {
		return notificacionRepository.findByIdPersona(idPersona);
	}

	@Override
	public List<Notificacion> findByIdExpediente(long idExpediente) {
		return notificacionRepository.findByIdExpediente(idExpediente);
	}

	@Override
	public List<Notificacion> findByNumeroExpediente(String numeroExpediente) {
		return notificacionRepository.findByNumeroExpediente(numeroExpediente);
	}

	@Override
	public Notificacion findById(String id) {
		Notificacion notificacion = notificacionRepository.findById(id).orElse(null);
		if(notificacion == null) {
			throw new NotFoundException("Not Found");
		}
		return notificacion;
	}

	@Override
	public Notificacion create(Notificacion notificacion) {
		notificacion.setFecha(new Date());
		notificacion.setState(0);
		Expediente expediente = expedienteRepository.findById(notificacion.getIdExpediente()).orElse(null);
		if(expediente == null) {
			throw new NotFoundException("Case Not Found");
		}
		notificacion.setIdPersona(expediente.getIdSolicitante());
		notificacion.setNumeroExpediente(expediente.getNumeroExpediente());
		return notificacionRepository.insert(notificacion);
	}

	@Override
	public Notificacion update(Notificacion notificacion) {
		Notificacion saved = notificacionRepository.findById(notificacion.getId()).orElse(null);
		if(saved == null) {
			throw new NotFoundException("Not Found");
		}
		saved.setState(1);
		return notificacionRepository.save(saved);
	}

	@Override
	public void delete(String id) {
		notificacionRepository.deleteById(id);
	}

}

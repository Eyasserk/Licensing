package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.Notificacion;

public interface NotificacionService {
	
	public List<Notificacion> findAll(int page, int size);
	
	public List<Notificacion> findAllByIdPersona(long idPersona);
	
	public List<Notificacion> findByIdExpediente(long idExpediente);
	
	public List<Notificacion> findByNumeroExpediente(String numeroExpediente);
	
	public Notificacion findById(String id);
	
	public Notificacion create(Notificacion notificacion);
	
	public Notificacion update(Notificacion notificacion);
	
	public void delete(String id);
}

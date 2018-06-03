package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.TipoExpediente;

public interface TipoExpedienteService {
	
	public List<TipoExpediente> findAll();
	
	public TipoExpediente findById(long id);
}

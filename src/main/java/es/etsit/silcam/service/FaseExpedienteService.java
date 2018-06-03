package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.FaseExpediente;

public interface FaseExpedienteService {
	
public List<FaseExpediente> findAll();
	
	public FaseExpediente findById(long id);
	
	public FaseExpediente create(FaseExpediente faseExpediente);
	
	public FaseExpediente update(FaseExpediente faseExpediente);
	
	public void delete(long id);

}

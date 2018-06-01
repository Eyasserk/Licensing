package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.FaseExpediente;
import es.etsit.silcam.filter.FaseExpedienteFilter;

public interface FaseExpedienteService {
	
public List<FaseExpediente> findAll(FaseExpedienteFilter filter);
	
	public FaseExpediente findById(long id);
	
	public FaseExpediente create(FaseExpediente faseExpediente);
	
	public FaseExpediente update(FaseExpediente faseExpediente);
	
	public void delete(long id);

}

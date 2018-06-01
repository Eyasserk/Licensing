package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.Expediente;
import es.etsit.silcam.filter.ExpedienteFilter;

public interface ExpedienteService {
	
	public List<Expediente> findAll(ExpedienteFilter filter);
	
	public Expediente findById(long id);
	
	public Expediente create(Expediente expediente);
	
	public Expediente update(Expediente expediente);
	
	public void delete(long id);
	
}

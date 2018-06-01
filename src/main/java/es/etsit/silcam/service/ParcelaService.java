package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.gis.Parcela;

public interface ParcelaService {

	public List<Parcela> findAll();
	
	public List<Parcela> findByIdExpediente(long idExpediente);
	
	public Parcela findById(String id);
	
	public Parcela create(Parcela parcela);
	
	public Parcela update(Parcela parcela);
	
}

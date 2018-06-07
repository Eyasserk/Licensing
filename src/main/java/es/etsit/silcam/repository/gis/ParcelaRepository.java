package es.etsit.silcam.repository.gis;

import java.util.List;

import es.etsit.silcam.entity.gis.Parcela;

public interface ParcelaRepository {
	
	public List<Parcela> findAll();
	
	public Parcela findById(String id);
	
	public Parcela findByIdExpediente(long idExpediente);
	
	public Parcela create(Parcela parcela);
	
	public Parcela update(Parcela parcela);
}

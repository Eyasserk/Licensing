package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.filter.ProvinciaFilter;

public interface ProvinciaService {

	public List<Provincia> findAll(ProvinciaFilter filter);
	
	public Provincia findById(long id);
	
	public Provincia create(Provincia provincia);
	
	public Provincia update(Provincia provincia);
	
	public void delete(long id);
}

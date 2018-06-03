package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.Pais;

public interface PaisService {
	
	public List<Pais> findAll();
	
	public Pais findById(long id);
	
	public Pais create(Pais pais);
	
	public Pais update(Pais pais);
	
	public void delete(long id);

}

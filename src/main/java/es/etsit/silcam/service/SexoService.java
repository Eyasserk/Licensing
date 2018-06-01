package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.Sexo;
import es.etsit.silcam.filter.SexoFilter;

public interface SexoService {
	
	public List<Sexo> findAll(SexoFilter filter);
	
	public Sexo findById(long id);
	
	public Sexo create(Sexo sexo);
	
	public Sexo update(Sexo sexo);
	
	public void delete(long id);
}

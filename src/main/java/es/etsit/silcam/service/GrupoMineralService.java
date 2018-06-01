package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.GrupoMineral;
import es.etsit.silcam.filter.GrupoMineralFilter;

public interface GrupoMineralService {

	public List<GrupoMineral> findAll(GrupoMineralFilter filter);
	
	public GrupoMineral findById(long id);
	
	public GrupoMineral create(GrupoMineral grupoMineral);
	
	public GrupoMineral update(GrupoMineral grupoMineral);
	
	public void delete(long id);
}

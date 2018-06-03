package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.GrupoMineral;

public interface GrupoMineralService {

	public List<GrupoMineral> findAll();
	
	public GrupoMineral findById(long id);
	
	public GrupoMineral create(GrupoMineral grupoMineral);
	
	public GrupoMineral update(GrupoMineral grupoMineral);
	
	public void delete(long id);
}

package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.Mineral;

public interface MineralService{

	public List<Mineral> findAll();
	
	public List<Mineral> findByGrupoId(long grupoId);
	
	public Mineral findById(long id);
	
	public Mineral create(Mineral mineral);
	
	public Mineral update(Mineral mineral);
	
	public void delete(long id);
}

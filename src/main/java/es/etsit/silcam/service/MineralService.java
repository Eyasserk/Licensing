package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.Mineral;
import es.etsit.silcam.filter.MineralFilter;

public interface MineralService{

	public List<Mineral> findAll(MineralFilter filter);
	
	public Mineral findById(long id);
	
	public Mineral create(Mineral mineral);
	
	public Mineral update(Mineral mineral);
	
	public void delete(long id);
}

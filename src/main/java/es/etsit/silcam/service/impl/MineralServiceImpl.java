package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.Mineral;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.filter.MineralFilter;
import es.etsit.silcam.repository.MineralRepository;
import es.etsit.silcam.service.MineralService;

@Service("mineralService")
public class MineralServiceImpl implements MineralService{

	
	private MineralRepository mineralRepository;
	
	@Autowired
	public void setmineralRepository(MineralRepository mineralRepository) {
		this.mineralRepository = mineralRepository;
	}
	
	@Override
	public List<Mineral> findAll(MineralFilter filter) {
		// TODO implement here the filter
		return mineralRepository.findAll();
	}

	@Override
	public Mineral findById(long id) {
		Mineral grupo = mineralRepository.getOne(id);
		if(grupo == null) {
			throw new NotFoundException("Mineral no encontrado");
		}
		return grupo;
	}

	@Override
	public Mineral create(Mineral mineral) {
		return mineralRepository.saveAndFlush(mineral);
	}

	@Override
	public Mineral update(Mineral mineral) {
		//TODO check null values
		return mineralRepository.saveAndFlush(mineral);
	}

	@Override
	public void delete(long id) {
		Mineral grupo = mineralRepository.getOne(id);
		if(grupo == null) {
			throw new NotFoundException("Mineral no encontrado");
		}
		mineralRepository.deleteById(id);
	}

}

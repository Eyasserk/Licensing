package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.GrupoMineral;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.repository.GrupoMineralRepository;
import es.etsit.silcam.service.GrupoMineralService;

@Service("grupoMineralService")
public class GrupoMineralServiceImpl implements GrupoMineralService{

	
	private GrupoMineralRepository grupoMineralRepository;
	
	@Autowired
	public void setGrupoMineralRepository(GrupoMineralRepository grupoMineralRepository) {
		this.grupoMineralRepository = grupoMineralRepository;
	}
	
	@Override
	public List<GrupoMineral> findAll() {
		return grupoMineralRepository.findAll();
	}

	@Override
	public GrupoMineral findById(long id) {
		GrupoMineral grupo = grupoMineralRepository.getOne(id);
		if(grupo == null) {
			throw new NotFoundException("Grupo no encontrado");
		}
		return grupo;
	}

	@Override
	public GrupoMineral create(GrupoMineral grupoMineral) {
		return grupoMineralRepository.saveAndFlush(grupoMineral);
	}

	@Override
	public GrupoMineral update(GrupoMineral grupoMineral) {
		//TODO check null values
		return grupoMineralRepository.saveAndFlush(grupoMineral);
	}

	@Override
	public void delete(long id) {
		GrupoMineral grupo = grupoMineralRepository.getOne(id);
		if(grupo == null) {
			throw new NotFoundException("Grupo no encontrado");
		}
		grupoMineralRepository.deleteById(id);
	}

}

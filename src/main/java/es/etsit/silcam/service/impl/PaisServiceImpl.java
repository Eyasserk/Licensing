package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.Pais;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.filter.PaisFilter;
import es.etsit.silcam.repository.PaisRepository;
import es.etsit.silcam.service.PaisService;

@Service("paisService")
public class PaisServiceImpl implements PaisService{

	
	private PaisRepository paisRepository;
	
	@Autowired
	public void setpaisRepository(PaisRepository paisRepository) {
		this.paisRepository = paisRepository;
	}
	
	@Override
	public List<Pais> findAll(PaisFilter filter) {
		// TODO implement here the filter
		return paisRepository.findAll();
	}

	@Override
	public Pais findById(long id) {
		Pais pais = paisRepository.getOne(id);
		if(pais == null) {
			throw new NotFoundException("Pais no encontrado");
		}
		return pais;
	}

	@Override
	public Pais create(Pais pais) {
		return paisRepository.saveAndFlush(pais);
	}

	@Override
	public Pais update(Pais pais) {
		//TODO check null values
		return paisRepository.saveAndFlush(pais);
	}

	@Override
	public void delete(long id) {
		Pais pais = paisRepository.getOne(id);
		if(pais == null) {
			throw new NotFoundException("Pais no encontrado");
		}
		paisRepository.deleteById(id);
	}

}

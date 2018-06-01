package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.filter.ProvinciaFilter;
import es.etsit.silcam.repository.ProvinciaRepository;
import es.etsit.silcam.service.ProvinciaService;

@Service("provinciaService")
public class ProvinciaServiceImpl implements ProvinciaService{

	
	private ProvinciaRepository provinciaRepository;
	
	@Autowired
	public void setprovinciaRepository(ProvinciaRepository provinciaRepository) {
		this.provinciaRepository = provinciaRepository;
	}
	
	@Override
	public List<Provincia> findAll(ProvinciaFilter filter) {
		// TODO implement here the filter
		return provinciaRepository.findAll();
	}

	@Override
	public Provincia findById(long id) {
		Provincia provincia = provinciaRepository.getOne(id);
		if(provincia == null) {
			throw new NotFoundException("Provincia no encontrado");
		}
		return provincia;
	}

	@Override
	public Provincia create(Provincia provincia) {
		return provinciaRepository.saveAndFlush(provincia);
	}

	@Override
	public Provincia update(Provincia provincia) {
		//TODO check null values
		return provinciaRepository.saveAndFlush(provincia);
	}

	@Override
	public void delete(long id) {
		Provincia provincia = provinciaRepository.getOne(id);
		if(provincia == null) {
			throw new NotFoundException("Provincia no encontrado");
		}
		provinciaRepository.deleteById(id);
	}

}

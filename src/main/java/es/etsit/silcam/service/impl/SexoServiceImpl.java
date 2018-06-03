package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.Sexo;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.repository.SexoRepository;
import es.etsit.silcam.service.SexoService;

@Service("sexoService")
public class SexoServiceImpl implements SexoService{

	
	private SexoRepository sexoRepository;
	
	@Autowired
	public void setsexoRepository(SexoRepository sexoRepository) {
		this.sexoRepository = sexoRepository;
	}
	
	@Override
	public List<Sexo> findAll() {
		return sexoRepository.findAll();
	}

	@Override
	public Sexo findById(long id) {
		Sexo grupo = sexoRepository.getOne(id);
		if(grupo == null) {
			throw new NotFoundException("Sexo no encontrado");
		}
		return grupo;
	}

	@Override
	public Sexo create(Sexo sexo) {
		return sexoRepository.saveAndFlush(sexo);
	}

	@Override
	public Sexo update(Sexo sexo) {
		//TODO check null values
		return sexoRepository.saveAndFlush(sexo);
	}

	@Override
	public void delete(long id) {
		Sexo grupo = sexoRepository.getOne(id);
		if(grupo == null) {
			throw new NotFoundException("Sexo no encontrado");
		}
		sexoRepository.deleteById(id);
	}

}

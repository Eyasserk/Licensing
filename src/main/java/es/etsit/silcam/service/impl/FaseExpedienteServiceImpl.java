package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.FaseExpediente;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.repository.FaseExpedienteRepository;
import es.etsit.silcam.service.FaseExpedienteService;

@Service("faseExpedienteServiceImpl")
public class FaseExpedienteServiceImpl implements FaseExpedienteService{

	private FaseExpedienteRepository faseExpedienteRepository;
	
	@Autowired
	public void setFaseExpedienteRepository(FaseExpedienteRepository faseExpedienteRepository) {
		this.faseExpedienteRepository = faseExpedienteRepository;
	}
	
	@Override
	public List<FaseExpediente> findAll() {
		return faseExpedienteRepository.findAll();
	}

	@Override
	public FaseExpediente findById(long id) {
		FaseExpediente fase = faseExpedienteRepository.getOne(id);
		if(fase == null) {
			throw new NotFoundException("Fase de expediente no encontrada");
		}
		return fase;
	}

	@Override
	public FaseExpediente create(FaseExpediente faseExpediente) {
		return faseExpedienteRepository.saveAndFlush(faseExpediente);
	}

	@Override
	public FaseExpediente update(FaseExpediente faseExpediente) {
		// TODO check null values
		return faseExpedienteRepository.saveAndFlush(faseExpediente);
	}

	@Override
	public void delete(long id) {
		FaseExpediente fase = faseExpedienteRepository.getOne(id);
		if(fase == null) {
			throw new NotFoundException("Fase expediente no encontrada");
		}
		faseExpedienteRepository.deleteById(id);
	}

}

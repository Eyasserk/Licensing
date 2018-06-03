package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.TipoExpediente;
import es.etsit.silcam.repository.TipoExpedienteRepository;
import es.etsit.silcam.service.TipoExpedienteService;

@Service("tipoExpedienteService")
public class TipoExpedienteServiceImpl implements TipoExpedienteService{

	
	private TipoExpedienteRepository tipoExpedienteRepository;
	
	@Autowired
	public void setTipoExpedienteRepository(TipoExpedienteRepository tipoExpedienteRepository) {
		this.tipoExpedienteRepository = tipoExpedienteRepository;
	}
	
	@Override
	public List<TipoExpediente> findAll() {
		return tipoExpedienteRepository.findAll();
	}

	@Override
	public TipoExpediente findById(long id) {
		return tipoExpedienteRepository.getOne(id);
	}

}

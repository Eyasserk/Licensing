package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.TipoSolicitud;
import es.etsit.silcam.repository.TipoSolicitudRepository;
import es.etsit.silcam.service.TipoSolicitudService;

@Service("tipoSolicitudService")
public class TipoSolicitudServiceImpl implements TipoSolicitudService{

	
	private TipoSolicitudRepository tipoSolicitudRepository;
	
	@Autowired
	public void setTipoSolicitudRepository(TipoSolicitudRepository tipoSolicitudRepository) {
		this.tipoSolicitudRepository = tipoSolicitudRepository;
	}
	
	@Override
	public List<TipoSolicitud> findAll() {
		return tipoSolicitudRepository.findAll();
	}

	@Override
	public TipoSolicitud findById(long id) {
		return tipoSolicitudRepository.getOne(id);
	}

}

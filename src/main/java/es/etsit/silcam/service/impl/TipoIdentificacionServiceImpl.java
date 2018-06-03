package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.TipoIdentificacion;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.repository.TipoIdentificacionRepository;
import es.etsit.silcam.service.TipoIdentificacionService;

@Service("tipoIdentificacionService")
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService{

	
	private TipoIdentificacionRepository tipoIdentificacionRepository;
	
	@Autowired
	public void settipoIdentificacionRepository(TipoIdentificacionRepository tipoIdentificacionRepository) {
		this.tipoIdentificacionRepository = tipoIdentificacionRepository;
	}
	
	@Override
	public List<TipoIdentificacion> findAll() {
		return tipoIdentificacionRepository.findAll();
	}

	@Override
	public TipoIdentificacion findById(long id) {
		TipoIdentificacion grupo = tipoIdentificacionRepository.getOne(id);
		if(grupo == null) {
			throw new NotFoundException("Tipo Identificacion no encontrado");
		}
		return grupo;
	}

	@Override
	public TipoIdentificacion create(TipoIdentificacion tipoIdentificacion) {
		return tipoIdentificacionRepository.saveAndFlush(tipoIdentificacion);
	}

	@Override
	public TipoIdentificacion update(TipoIdentificacion tipoIdentificacion) {
		//TODO check null values
		return tipoIdentificacionRepository.saveAndFlush(tipoIdentificacion);
	}

	@Override
	public void delete(long id) {
		TipoIdentificacion grupo = tipoIdentificacionRepository.getOne(id);
		if(grupo == null) {
			throw new NotFoundException("Tipo Identificacion no encontrado");
		}
		tipoIdentificacionRepository.deleteById(id);
	}

}

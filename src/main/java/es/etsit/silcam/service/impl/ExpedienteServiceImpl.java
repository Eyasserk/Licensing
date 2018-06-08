package es.etsit.silcam.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.EstadoSolicitud;
import es.etsit.silcam.entity.Expediente;
import es.etsit.silcam.entity.FaseExpediente;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.filter.ExpedienteFilter;
import es.etsit.silcam.repository.EstadoSolicitudRepository;
import es.etsit.silcam.repository.ExpedienteRepository;
import es.etsit.silcam.repository.FaseExpedienteRepository;
import es.etsit.silcam.repository.gis.ParcelaRepository;
import es.etsit.silcam.service.ExpedienteService;
import lombok.extern.slf4j.Slf4j;

@Service("expedienteService")
@Slf4j
public class ExpedienteServiceImpl implements ExpedienteService{

	private ExpedienteRepository expedienteRepository;
	private ParcelaRepository parcelaRepository;
	private EstadoSolicitudRepository estadoSolicitudRepository;
	private FaseExpedienteRepository faseExpedienteRepository;
	
	@Autowired
	public void setExpedienteRepository(ExpedienteRepository expedienteRepository) {
		this.expedienteRepository = expedienteRepository;
	}
	
	@Autowired
	public void setParcelaRepository(ParcelaRepository parcelaRepository) {
		this.parcelaRepository = parcelaRepository;
	}
	
	@Autowired
	public void setEstadoSolicitudRepository(EstadoSolicitudRepository estadoSolicitudRepository) {
		this.estadoSolicitudRepository = estadoSolicitudRepository;
	}
	
	@Autowired
	public void setFaseExpedienteRepository(FaseExpedienteRepository faseExpedienteRepository) {
		this.faseExpedienteRepository = faseExpedienteRepository;
	}
	
	@Override
	public List<Expediente> findAll(ExpedienteFilter filter){
		if(filter == null) {
			return expedienteRepository.findAll();
		}else{
			return expedienteRepository.findAll(filter.getSpecifications(),
												filter.getPageRequest())
												.getContent();
		}
	}
	
	@Override
	public Expediente findById(long id) {
		Expediente expediente = expedienteRepository.getOne(id);
		if(expediente == null) {
			throw new NotFoundException("Expediente no encontrado");
		}
		expediente.setParcela(parcelaRepository.findByIdExpediente(expediente.getId()));
		return expediente;
	}
	
	@Override
	public Expediente create(Expediente expediente) {
		//Fecha inicio actual
		expediente.setFechaInicioExpediente(new Date());
		
		//Asignar estado y fase iniciales
		FaseExpediente fase = faseExpedienteRepository.getOne(1L);
		EstadoSolicitud estado = estadoSolicitudRepository.getOne(1L);
		expediente.setEstado(estado);
		expediente.setFase(fase);
		
		//Asignar numero de expediente
		expediente.setNumeroExpediente(generateNumeroExpediente(expediente));
		
		log.info("Expediente para guardar: {}",expediente);
		
		//Guardarlo en BBDD
		Expediente saved = expedienteRepository.save(expediente);
		
		//Guardar la parcela
		if(expediente.getParcela() != null) {
			expediente.getParcela().setExpedienteId(saved.getId());
			expediente.setParcela(parcelaRepository.create(expediente.getParcela()));
		}
		saved.setParcela(expediente.getParcela());
		log.info("Saved: {}",saved);
		return saved;
	}
	
	@Override
	public Expediente update(Expediente expediente) {
		return expedienteRepository.save(expediente);
	}
	
	@Override
	public void delete(long id) {
		Expediente expediente = expedienteRepository.getOne(id);
		if(expediente == null) {
			throw new NotFoundException("Expediente no encontrado");
		}
		expedienteRepository.deleteById(id);
	}
	
	private String generateNumeroExpediente(Expediente expediente) {
		StringBuilder sb = new StringBuilder();
		sb.append(new SimpleDateFormat("yyyyMM").format(new Date()));
		sb.append("-");
		sb.append("01"); //01: Concesion
		sb.append("-");
		sb.append(new Date().getTime());
		return sb.toString();
	}
	
}


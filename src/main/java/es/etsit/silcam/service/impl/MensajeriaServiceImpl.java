package es.etsit.silcam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.Hilo;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.repository.MensajeriaRepository;
import es.etsit.silcam.service.MensajeriaService;
import es.etsit.silcam.util.Mensaje;

@Service("mensajeriaService")
public class MensajeriaServiceImpl implements MensajeriaService{

	private MensajeriaRepository mensajeriaRepository;
	
	@Autowired
	public void setMensajeriaRepository(MensajeriaRepository mensajeriaRepository) {
		this.mensajeriaRepository = mensajeriaRepository;
	}
	
	@Override
	public List<Hilo> findAll(int page, int size) {
		return mensajeriaRepository.findAll(PageRequest.of(page, size)).getContent();
	}
	
	@Override
	public Hilo findById(String id) {
		Hilo hilo = mensajeriaRepository.findById(id).orElse(null);
		if(hilo == null) {
			throw new NotFoundException("Not found");
		}
		return hilo;
	}

	@Override
	public List<Hilo> findByIdExpediente(long idExpediente) {
		return mensajeriaRepository.findByIdExpediente(idExpediente);
	}

	@Override
	public Hilo create(Hilo hilo) {
		return mensajeriaRepository.insert(hilo);	
	}

	@Override
	public Hilo update(Hilo hilo) {
		Hilo saved = mensajeriaRepository.findById(hilo.getId()).orElse(null);
		if(saved == null) {
			throw new NotFoundException("Not found");
		}
		return mensajeriaRepository.save(hilo);
	}

	@Override
	public void delete(String id) {
		mensajeriaRepository.deleteById(id);
	}

	@Override
	public Hilo addMensaje(String idHilo, Mensaje mensaje) {
		Hilo hilo = mensajeriaRepository.findById(idHilo).orElse(null);
		if(hilo != null) {
			hilo.getMensajes().add(mensaje);
			return mensajeriaRepository.save(hilo);
		}else {
			throw new NotFoundException("Not found");
		}
	}

}

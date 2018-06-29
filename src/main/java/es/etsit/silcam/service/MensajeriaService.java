package es.etsit.silcam.service;

import java.util.List;

import es.etsit.silcam.entity.Hilo;
import es.etsit.silcam.util.Mensaje;

public interface MensajeriaService {
	
	public List<Hilo> findAll(int page, int size);
	
	public Hilo findById(String id);
	
	public List<Hilo> findByIdExpediente(long idExpediente);
	
	public Hilo create(Hilo hilo);
	
	public Hilo update(Hilo hilo);
	
	public void delete(String id);
	
	public Hilo addMensaje(String idHilo, Mensaje mensaje);

}

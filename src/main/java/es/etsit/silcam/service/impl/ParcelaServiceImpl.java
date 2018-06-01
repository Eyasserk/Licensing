package es.etsit.silcam.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.etsit.silcam.entity.gis.Parcela;
import es.etsit.silcam.repository.gis.ParcelaRepository;
import es.etsit.silcam.service.ParcelaService;

@Service("parcelaService")
public class ParcelaServiceImpl implements ParcelaService{
	
	ParcelaRepository parcelaRepository;

	@Autowired
	private void setParcelaRepository(ParcelaRepository parcelaRepository) {
		this.parcelaRepository = parcelaRepository;
	}
	
	@Override
	public List<Parcela> findAll() {
		return parcelaRepository.findAll();
	}

	@Override
	public List<Parcela> findByIdExpediente(long idExpediente) {
		return parcelaRepository.findByIdExpediente(idExpediente);
	}

	@Override
	public Parcela findById(String id) {
		return parcelaRepository.findById(id);
	}

	@Override
	public Parcela create(Parcela parcela) {
		parcela.setId(UUID.randomUUID().toString());
		return parcelaRepository.create(parcela);
	}

	@Override
	public Parcela update(Parcela parcela) {
		return parcelaRepository.update(parcela);
	}

}

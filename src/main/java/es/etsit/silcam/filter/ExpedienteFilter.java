package es.etsit.silcam.filter;

import java.util.Date;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import es.etsit.silcam.core.AbstractPageableFilter;
import es.etsit.silcam.entity.EstadoSolicitud;
import es.etsit.silcam.entity.Expediente;
import es.etsit.silcam.entity.FaseExpediente;
import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.entity.PersonaJuridica;
import es.etsit.silcam.exception.BadRequestException;
import es.etsit.silcam.filter.model.Expediente_;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpedienteFilter extends AbstractPageableFilter<Expediente>{
	
	private static final String LIKE = "%";

	private Long id;
	private String numeroExpediente;
	private Date fechaInicioStart;
	private Date fechaInicioEnd;
	private Long faseExpedienteId;
	private Long estadoSolicitudId;
	private Long idSolicitante;
	private Long mineralId;
	private Long grupoMineralId;
	private Long provinciaId;
	private Long tipoSolicitanteId;
	
	@Override
	public Specification<Expediente> getSpecifications() {
		Specification<Expediente> specById = null;
		Specification<Expediente> specByNumeroExpediente = null;
		Specification<Expediente> specByFechaInicio = null;
		Specification<Expediente> specByFase = null;
		Specification<Expediente> specByEstado = null;
		Specification<Expediente> specByIdSolicitante = null;
		Specification<Expediente> specByMineral = null;
		Specification<Expediente> specByGrupoMineral = null;
		Specification<Expediente> specByProvincia = null;
		Specification<Expediente> specByTipoSolicitante = null;
		
		if(id != null) {
			specById = getSpecificationById();
		}
		if(numeroExpediente != null && !numeroExpediente.isEmpty()) {
			specByNumeroExpediente = getSpecificationByNumeroExpediente();
		}
		if(fechaInicioStart != null || fechaInicioEnd != null) {
			specByFechaInicio = getSpecificationByFechaInicio();
		}
		if(estadoSolicitudId != null) {
			specByEstado = getSpecificationByEstado();
		}
		if(faseExpedienteId != null) {
			specByFase = getSpecificationByFase();
		}
		if(idSolicitante != null) {
			if(tipoSolicitanteId == null) {
				throw new BadRequestException("Especifique el tipo de persona");
			}
			specByIdSolicitante = getSpecificationByIdSolicitante();
		}
		if(mineralId != null) {
			specByMineral = getSpecificationByMineral();
		}
		if(grupoMineralId != null) {
			specByGrupoMineral = getSpecificationByGrupoMineral();
		}
		if(provinciaId != null) {
			specByProvincia = getSpecificationByProvincia();
		}
		if(tipoSolicitanteId != null) {
			specByTipoSolicitante = getSpecificationByTipoSolicitante();
		}
		return Specification.where(specById)
				.and(specByNumeroExpediente)
				.and(specByFechaInicio)
				.and(specByEstado)
				.and(specByFase)
				.and(specByIdSolicitante)
				.and(specByMineral)
				.and(specByGrupoMineral)
				.and(specByProvincia)
				.and(specByTipoSolicitante);	
		
	}
	
	private Specification<Expediente> getSpecificationById(){
		return (root, query, cb) -> cb.equal(root.<Long> get(Expediente_.id), id);
	}
	
	private Specification<Expediente> getSpecificationByNumeroExpediente(){
		return (root, query, cb) -> cb.like(
				cb.lower(root.<String> get(Expediente_.numeroExpediente)),
				LIKE + numeroExpediente.toLowerCase() + LIKE);
	}
	
	private Specification<Expediente> getSpecificationByFechaInicio(){
		return (root, query, cb) -> { 
			Predicate p = null;
			if((fechaInicioStart!=null) && (fechaInicioEnd!=null)){
				p = cb.between(root.<Date>get(Expediente_.fechaInicio ), fechaInicioStart,fechaInicioEnd);
			}
			if((fechaInicioStart!=null) && (fechaInicioEnd==null))
			{
				p = cb.greaterThanOrEqualTo(root.<Date>get(Expediente_.fechaInicio ), fechaInicioStart);
			}
			if((fechaInicioStart==null) && (fechaInicioEnd!=null)){
				p = cb.lessThanOrEqualTo(root.<Date>get(Expediente_.fechaInicio ), fechaInicioEnd);
			}  
			return p; 
		}; 
	}
	
	private Specification<Expediente> getSpecificationByFase(){
		return (root, query, cb) -> {
			FaseExpediente fase = new FaseExpediente();
			fase.setId(faseExpedienteId);
			return cb.equal(root.<FaseExpediente>get(Expediente_.fase), fase);
		};
	}
	
	private Specification<Expediente> getSpecificationByEstado(){
		return (root, query, cb) -> {
			EstadoSolicitud estado = new EstadoSolicitud();
			estado.setId(estadoSolicitudId);
			return cb.equal(root.<EstadoSolicitud>get(Expediente_.estado), estado);
		};
	}
	
	private Specification<Expediente> getSpecificationByIdSolicitante(){
		return (root, query, cb) -> {
			Predicate p = null;
			if(tipoSolicitanteId == 1L) {
				//Persona fisica
				PersonaFisica persona1 = new PersonaFisica();
				persona1.setId(idSolicitante);
				
				p = cb.equal(root.<PersonaFisica>get(Expediente_.solicitanteFisico), persona1);
			}else if(tipoSolicitanteId == 2L){
				//Persona juridica
				PersonaJuridica persona2 = new PersonaJuridica();
				persona2.setId(idSolicitante);
				
				p = cb.equal(root.<PersonaJuridica>get(Expediente_.solicitanteJuridico), persona2);
			}
			return p;
		};
	}
	
	private Specification<Expediente> getSpecificationByMineral(){
		return (root, query, cb) -> null; 
	}
	
	private Specification<Expediente> getSpecificationByGrupoMineral(){
		return (root, query, cb) -> null;
	}
	
	private Specification<Expediente> getSpecificationByProvincia(){
		return (root, query, cb) -> null;
	}
	
	private Specification<Expediente> getSpecificationByTipoSolicitante(){
		/**
		return (root, query, cb) -> {
			TipoPersona tipo = new TipoPersona();
			tipo.setId(tipoSolicitanteId);
			return cb.equal(root.<TipoPersona>get(Expediente_.tipoSolicitante), tipo);
		};
		*/
		return null;
	}
	
	@Override
	public void reset() {
		this.id = null;
		this.numeroExpediente = null;
		this.fechaInicioStart = null;
		this.fechaInicioEnd = null;
		this.faseExpedienteId = null;
		this.estadoSolicitudId = null;
		this.idSolicitante = null;
		this.mineralId = null;
		this.grupoMineralId = null;
		this.provinciaId = null;
		this.tipoSolicitanteId = null;
	}

}

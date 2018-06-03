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
import es.etsit.silcam.entity.TipoExpediente;
import es.etsit.silcam.entity.TipoSolicitud;
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
	private Date fechaInicioExpedienteStart;
	private Date fechaInicioExpedienteEnd;
	private Date fechaInicioActividadStart;
	private Date fechaInicioActividadEnd;
	private Date fechaFinActividadStart;
	private Date fechaFinActividadEnd;
	private Long tipoExpedienteId;
	private Long tipoSolicitudId;
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
		Specification<Expediente> specByFechaInicioExpediente = null;
		Specification<Expediente> specByFechaInicioActividad = null;
		Specification<Expediente> specByFechaFinActividad = null;
		Specification<Expediente> specByFase = null;
		Specification<Expediente> specByEstado = null;
		Specification<Expediente> specByTipoExpediente = null;
		Specification<Expediente> specByTipoSolicitud = null;
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
		if(fechaInicioExpedienteStart != null || fechaInicioExpedienteEnd != null) {
			specByFechaInicioExpediente = getSpecificationByFechaInicioExpediente();
		}
		if(fechaInicioActividadStart != null || fechaInicioActividadEnd != null) {
			specByFechaInicioActividad = getSpecificationByFechaInicioActividad();
		}
		if(fechaFinActividadStart != null || fechaFinActividadEnd != null) {
			specByFechaFinActividad = getSpecificationByFechaFinActividad();
		}
		if(estadoSolicitudId != null) {
			specByEstado = getSpecificationByEstado();
		}
		if(faseExpedienteId != null) {
			specByFase = getSpecificationByFase();
		}
		if(tipoExpedienteId != null) {
			specByTipoExpediente = getSpecificationByTipoExpediente();
		}
		if(tipoSolicitanteId != null) {
			specByTipoSolicitud = getSpecificationByTipoSolicitud();
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
				.and(specByFechaInicioExpediente)
				.and(specByFechaInicioActividad)
				.and(specByFechaFinActividad)
				.and(specByEstado)
				.and(specByFase)
				.and(specByTipoExpediente)
				.and(specByTipoSolicitud)
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
	
	private Specification<Expediente> getSpecificationByFechaInicioExpediente(){
		return (root, query, cb) -> { 
			Predicate p = null;
			if((fechaInicioExpedienteStart!=null) && (fechaInicioExpedienteEnd!=null)){
				p = cb.between(root.<Date>get(Expediente_.fechaInicioExpediente ), fechaInicioExpedienteStart,fechaInicioExpedienteEnd);
			}
			if((fechaInicioExpedienteStart!=null) && (fechaInicioExpedienteEnd==null))
			{
				p = cb.greaterThanOrEqualTo(root.<Date>get(Expediente_.fechaInicioExpediente ), fechaInicioExpedienteStart);
			}
			if((fechaInicioExpedienteStart==null) && (fechaInicioExpedienteEnd!=null)){
				p = cb.lessThanOrEqualTo(root.<Date>get(Expediente_.fechaInicioExpediente ), fechaInicioExpedienteEnd);
			}  
			return p; 
		}; 
	}
	
	private Specification<Expediente> getSpecificationByFechaInicioActividad(){
		return (root, query, cb) -> { 
			Predicate p = null;
			if((fechaInicioActividadStart!=null) && (fechaInicioActividadEnd!=null)){
				p = cb.between(root.<Date>get(Expediente_.fechaInicioActividad ), fechaInicioActividadStart,fechaInicioActividadEnd);
			}
			if((fechaInicioActividadStart!=null) && (fechaInicioActividadEnd==null))
			{
				p = cb.greaterThanOrEqualTo(root.<Date>get(Expediente_.fechaInicioActividad ), fechaInicioActividadStart);
			}
			if((fechaInicioActividadStart==null) && (fechaInicioActividadEnd!=null)){
				p = cb.lessThanOrEqualTo(root.<Date>get(Expediente_.fechaInicioActividad ), fechaInicioActividadEnd);
			}  
			return p; 
		}; 
	}
	
	private Specification<Expediente> getSpecificationByFechaFinActividad(){
		return (root, query, cb) -> { 
			Predicate p = null;
			if((fechaFinActividadStart!=null) && (fechaFinActividadEnd!=null)){
				p = cb.between(root.<Date>get(Expediente_.fechaFinActividad ), fechaFinActividadStart,fechaFinActividadEnd);
			}
			if((fechaFinActividadStart!=null) && (fechaFinActividadEnd==null))
			{
				p = cb.greaterThanOrEqualTo(root.<Date>get(Expediente_.fechaFinActividad ), fechaFinActividadStart);
			}
			if((fechaFinActividadStart==null) && (fechaFinActividadEnd!=null)){
				p = cb.lessThanOrEqualTo(root.<Date>get(Expediente_.fechaFinActividad ), fechaFinActividadEnd);
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
	
	private Specification<Expediente> getSpecificationByTipoExpediente(){
		return (root, query, cb) -> {
			TipoExpediente tipoExpediente = new TipoExpediente();
			tipoExpediente.setId(tipoExpedienteId);
			return cb.equal(root.<TipoExpediente>get(Expediente_.tipoExpediente), tipoExpediente);
		};
	}
	
	private Specification<Expediente> getSpecificationByTipoSolicitud(){
		return (root, query, cb) -> {
			TipoSolicitud tipoSolicitud = new TipoSolicitud();
			tipoSolicitud.setId(tipoSolicitudId);
			return cb.equal(root.<TipoSolicitud>get(Expediente_.tipoSolicitud), tipoSolicitud);
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
		this.fechaInicioExpedienteStart = null;
		this.fechaInicioExpedienteEnd = null;
		this.fechaInicioActividadStart = null;
		this.fechaInicioActividadEnd = null;
		this.fechaFinActividadStart = null;
		this.fechaFinActividadEnd = null;
		this.faseExpedienteId = null;
		this.estadoSolicitudId = null;
		this.tipoExpedienteId = null;
		this.tipoSolicitudId = null;
		this.idSolicitante = null;
		this.mineralId = null;
		this.grupoMineralId = null;
		this.provinciaId = null;
		this.tipoSolicitanteId = null;
	}

}

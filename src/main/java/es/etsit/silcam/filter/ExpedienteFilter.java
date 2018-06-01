package es.etsit.silcam.filter;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import es.etsit.silcam.entity.EstadoSolicitud;
import es.etsit.silcam.entity.Expediente;
import es.etsit.silcam.entity.FaseExpediente;
import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.entity.PersonaJuridica;
import es.etsit.silcam.entity.TipoPersona;
import es.etsit.silcam.exception.BadRequestException;
import es.etsit.silcam.filter.model.Expediente_;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpedienteFilter extends AbstractFilter<Expediente>{
	
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
		return Specifications.where(specById)
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
		return new Specification<Expediente>() {
			@Override
			public Predicate toPredicate(Root<Expediente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Long> get(Expediente_.id), id);
			}
		};
	}
	
	private Specification<Expediente> getSpecificationByNumeroExpediente(){
		return new Specification<Expediente>() {
			@Override
			public Predicate toPredicate(Root<Expediente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(
						cb.lower(root.<String> get(Expediente_.numeroExpediente)),
						LIKE + numeroExpediente.toLowerCase() + LIKE);
			}
			
		};
	}
	
	private Specification<Expediente> getSpecificationByFechaInicio(){
		return new Specification<Expediente>() {			
			@Override
			public Predicate toPredicate(Root<Expediente> root, CriteriaQuery<?> query, CriteriaBuilder cb) { 
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
			}
		}; 
	}
	
	private Specification<Expediente> getSpecificationByFase(){
		return new Specification<Expediente>() {
			@Override
			public Predicate toPredicate(Root<Expediente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				FaseExpediente fase = new FaseExpediente();
				fase.setId(faseExpedienteId);
				return cb.equal(root.<FaseExpediente>get(Expediente_.fase), fase);
			}
		};
	}
	
	private Specification<Expediente> getSpecificationByEstado(){
		return new Specification<Expediente>() {
			@Override
			public Predicate toPredicate(Root<Expediente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				EstadoSolicitud estado = new EstadoSolicitud();
				estado.setId(estadoSolicitudId);
				return cb.equal(root.<EstadoSolicitud>get(Expediente_.estado), estado);
			}
		};
	}
	
	private Specification<Expediente> getSpecificationByIdSolicitante(){
		return new Specification<Expediente>() {
			@Override
			public Predicate toPredicate(Root<Expediente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p = null;
				if(tipoSolicitanteId == 1L) {
					//Persona fisica
					PersonaFisica persona = new PersonaFisica();
					persona.setId(idSolicitante);
					
					p = cb.equal(root.<PersonaFisica>get(Expediente_.solicitanteFisico), persona);
				}else if(tipoSolicitanteId == 2L){
					//Persona juridica
					PersonaJuridica persona = new PersonaJuridica();
					persona.setId(idSolicitante);
					
					p = cb.equal(root.<PersonaJuridica>get(Expediente_.solicitanteJuridico), persona);
				}
				return p;
			}
		};
	}
	
	private Specification<Expediente> getSpecificationByMineral(){
		return new Specification<Expediente>() {
			@Override
			public Predicate toPredicate(Root<Expediente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//TODO implement predicate here
				return null;
			}
		}; 
	}
	
	private Specification<Expediente> getSpecificationByGrupoMineral(){
		return new Specification<Expediente>() {
			@Override
			public Predicate toPredicate(Root<Expediente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//TODO implement predicate here
				return null;
			}
		};
	}
	
	private Specification<Expediente> getSpecificationByProvincia(){
		return new Specification<Expediente>() {
			@Override
			public Predicate toPredicate(Root<Expediente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//TODO implement predicate here
				return null;
			}
		};
	}
	
	private Specification<Expediente> getSpecificationByTipoSolicitante(){
		return new Specification<Expediente>() {
			@Override
			public Predicate toPredicate(Root<Expediente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				TipoPersona tipo = new TipoPersona();
				tipo.setId(tipoSolicitanteId);
				return cb.equal(root.<TipoPersona>get(Expediente_.tipoSolicitante), tipo);
			}
		};
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

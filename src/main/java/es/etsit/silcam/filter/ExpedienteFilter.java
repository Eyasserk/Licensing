package es.etsit.silcam.filter;

import java.util.Date;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import es.etsit.silcam.core.AbstractPageableFilter;
import es.etsit.silcam.entity.EstadoSolicitud;
import es.etsit.silcam.entity.Expediente;
import es.etsit.silcam.entity.FaseExpediente;
import es.etsit.silcam.entity.GrupoMineral;
import es.etsit.silcam.entity.Mineral;
import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.entity.TipoExpediente;
import es.etsit.silcam.entity.TipoPersona;
import es.etsit.silcam.entity.TipoSolicitud;
import es.etsit.silcam.exception.BadRequestException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExpedienteFilter extends AbstractPageableFilter<Expediente>{
	
	private static final String LIKE = "%";

	private Long id;
	private String numeroExpediente;
	private String numeroExpedienteLike;
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
		Specification<Expediente> specByNumeroExpedienteLike = null;
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
		if(numeroExpedienteLike != null && !numeroExpedienteLike.isEmpty()) {
			specByNumeroExpedienteLike = getSpecificationByNumeroExpedienteLike();
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
				.and(specByNumeroExpedienteLike)
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
		return (root, query, cb) -> cb.equal(root.<Long>get("id"), id);
	}
	
	private Specification<Expediente> getSpecificationByNumeroExpediente(){
		return (root, query, cb) -> cb.equal(
				cb.lower(root.<String>get("numeroExpediente")),
				numeroExpediente.toLowerCase());
	}
	
	private Specification<Expediente> getSpecificationByNumeroExpedienteLike(){
		return (root, query, cb) -> cb.like(
				cb.lower(root.<String>get("numeroExpediente")),
				LIKE + numeroExpedienteLike.toLowerCase() + LIKE);
	}
	
	private Specification<Expediente> getSpecificationByFechaInicioExpediente(){
		return (root, query, cb) -> { 
			Predicate p = null;
			if((fechaInicioExpedienteStart!=null) && (fechaInicioExpedienteEnd!=null)){
				p = cb.between(root.<Date>get("fechaInicioExpediente"), fechaInicioExpedienteStart,fechaInicioExpedienteEnd);
			}
			if((fechaInicioExpedienteStart!=null) && (fechaInicioExpedienteEnd==null))
			{
				p = cb.greaterThanOrEqualTo(root.<Date>get("fechaInicioExpediente"), fechaInicioExpedienteStart);
			}
			if((fechaInicioExpedienteStart==null) && (fechaInicioExpedienteEnd!=null)){
				p = cb.lessThanOrEqualTo(root.<Date>get("fechaInicioExpediente"), fechaInicioExpedienteEnd);
			}  
			return p; 
		}; 
	}
	
	private Specification<Expediente> getSpecificationByFechaInicioActividad(){
		return (root, query, cb) -> { 
			Predicate p = null;
			if((fechaInicioActividadStart!=null) && (fechaInicioActividadEnd!=null)){
				p = cb.between(root.<Date>get("fechaInicioActividad"), fechaInicioActividadStart,fechaInicioActividadEnd);
			}
			if((fechaInicioActividadStart!=null) && (fechaInicioActividadEnd==null))
			{
				p = cb.greaterThanOrEqualTo(root.<Date>get("fechaInicioActividad"), fechaInicioActividadStart);
			}
			if((fechaInicioActividadStart==null) && (fechaInicioActividadEnd!=null)){
				p = cb.lessThanOrEqualTo(root.<Date>get("fechaInicioActividad"), fechaInicioActividadEnd);
			}  
			return p; 
		}; 
	}
	
	private Specification<Expediente> getSpecificationByFechaFinActividad(){
		return (root, query, cb) -> { 
			Predicate p = null;
			if((fechaFinActividadStart!=null) && (fechaFinActividadEnd!=null)){
				p = cb.between(root.<Date>get("fechaFinActividad"), fechaFinActividadStart,fechaFinActividadEnd);
			}
			if((fechaFinActividadStart!=null) && (fechaFinActividadEnd==null))
			{
				p = cb.greaterThanOrEqualTo(root.<Date>get("fechaFinActividad"), fechaFinActividadStart);
			}
			if((fechaFinActividadStart==null) && (fechaFinActividadEnd!=null)){
				p = cb.lessThanOrEqualTo(root.<Date>get("fechaFinActividad"), fechaFinActividadEnd);
			}  
			return p; 
		}; 
	}
	
	private Specification<Expediente> getSpecificationByFase(){
		return (root, query, cb) -> {
			return cb.equal(root.<FaseExpediente>get("fase").<Long>get("id"), faseExpedienteId);
		};
	}
	
	private Specification<Expediente> getSpecificationByEstado(){
		return (root, query, cb) -> {
			return cb.equal(root.<EstadoSolicitud>get("estado").<Long>get("id"), estadoSolicitudId);
		};
	}
	
	private Specification<Expediente> getSpecificationByTipoExpediente(){
		return (root, query, cb) -> {
			return cb.equal(root.<TipoExpediente>get("tipoExpediente").<Long>get("id"), tipoExpedienteId);
		};
	}
	
	private Specification<Expediente> getSpecificationByTipoSolicitud(){
		return (root, query, cb) -> {
			return cb.equal(root.<TipoSolicitud>get("tipoSolicitud").<Long>get("id"), tipoSolicitanteId);
		};
	}
	
	private Specification<Expediente> getSpecificationByIdSolicitante(){
		return (root, query, cb) -> {
			return cb.equal(root.<Long>get("idSolicitante"), idSolicitante);
		};
	}
	
	private Specification<Expediente> getSpecificationByMineral(){
		return (root, query, cb) -> {
			return cb.equal(root.<Mineral>get("mineral").<Long>get("id"), mineralId);
		}; 
	}
	
	private Specification<Expediente> getSpecificationByGrupoMineral(){
		return (root, query, cb) -> {
			return cb.equal(root.<Mineral>get("mineral").<GrupoMineral>get("grupo").<Long>get("id"), grupoMineralId);
		};
	}
	
	private Specification<Expediente> getSpecificationByProvincia(){
		return (root, query, cb) -> {
			return cb.equal(root.<Provincia>get("provincia").<Long>get("id"), provinciaId);
		};
	}
	
	private Specification<Expediente> getSpecificationByTipoSolicitante(){
		return (root, query, cb) -> {
			return cb.equal(root.<TipoPersona>get("tipoSolicitante").<Long>get("id"), tipoSolicitanteId);
		};
	}
	
	@Override
	public void reset() {
		this.id = null;
		this.numeroExpediente = null;
		this.numeroExpedienteLike = null;
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
		this.tipoSolicitanteId = null;
		this.provinciaId = null;
	}

}

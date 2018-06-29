package es.etsit.silcam.filter;

import org.springframework.data.jpa.domain.Specification;

import es.etsit.silcam.core.AbstractPageableFilter;
import es.etsit.silcam.entity.Pais;
import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.entity.PersonaJuridica;
import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.entity.TipoIdentificacion;
import es.etsit.silcam.util.HashUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonaJuridicaFilter extends AbstractPageableFilter<PersonaJuridica>{
	
	private static final String LIKE = "%";
	
	private Long id;
	private String numeroIdentificacion;
	private String numeroIdentificacionLike;
	private Long tipoIdentificationId;
	private String token;
	private Boolean activo;
	private Long nacionalidadId;
	private Long provinciaId;
	private String razonSocialLike;
	private Long representanteId;
	
	@Override
	public Specification<PersonaJuridica> getSpecifications() {
		Specification<PersonaJuridica> specById = null;
		Specification<PersonaJuridica> specByNumeroIdentificacion = null;
		Specification<PersonaJuridica> specByNumeroIdentificacionLike = null;
		Specification<PersonaJuridica> specByTipoIdentificationId = null;
		Specification<PersonaJuridica> specByToken = null;
		Specification<PersonaJuridica> specByActivo = null;
		Specification<PersonaJuridica> specByNacionalidadId = null;
		Specification<PersonaJuridica> specByProvinciaId = null;
		Specification<PersonaJuridica> specByRazonSocialLike = null;
		Specification<PersonaJuridica> specByRepresentanteId = null;
		
		
		if(id != null) {
			specById = getSpecificationById();
		}
		if(numeroIdentificacion != null) {
			specByNumeroIdentificacion = getSpecificationByNumeroIdentificacion();
		}
		if(numeroIdentificacionLike != null) {
			specByNumeroIdentificacionLike = getSpecificationByNumeroIdentificacionLike();
		}
		if(tipoIdentificationId != null) {
			specByTipoIdentificationId = getSpecificationByTipoIdentificationId();
		}
		if(token != null) {
			specByToken = getSpecificationByToken();
		}
		if(activo != null) {
			specByActivo = getSpecificationByActivo();
		}
		if(nacionalidadId != null) {
			specByNacionalidadId = getSpecificationByNacionalidadId();
		}
		if(provinciaId != null) {
			specByProvinciaId = getSpecificationByProvinciaId();
		}
		if(razonSocialLike != null) {
			specByRazonSocialLike = getSpecificationByRazonSocialLike();
		}
		if(representanteId != null) {
			specByRepresentanteId = getSpecificationByRepresentanteId();
		}
		return Specification.where(specById)
							.and(specByNumeroIdentificacion)
							.and(specByNumeroIdentificacionLike)
							.and(specByTipoIdentificationId)
							.and(specByToken)
							.and(specByActivo)
							.and(specByNacionalidadId)
							.and(specByProvinciaId)
							.and(specByRazonSocialLike)
							.and(specByRepresentanteId);
	}
	
	private Specification<PersonaJuridica> getSpecificationById(){
		return (root, query, cb) -> cb.equal(root.<Long>get("id"), id);
	}
	
	private Specification<PersonaJuridica> getSpecificationByNumeroIdentificacion(){
		return (root, query, cb) -> cb.equal(cb.lower(root.<String>get("numeroIdentificacion")), numeroIdentificacion.toLowerCase());
	}
	
	private Specification<PersonaJuridica> getSpecificationByNumeroIdentificacionLike(){
		return (root, query, cb) -> cb.like(
				cb.lower(root.<String>get("numeroExpediente")),
				LIKE + numeroIdentificacionLike.toLowerCase() + LIKE);
	}
	
	private Specification<PersonaJuridica> getSpecificationByTipoIdentificationId(){
		return (root, query, cb) -> {
			return cb.equal(root.<TipoIdentificacion>get("tipoIdentificacion").<Long>get("id"), tipoIdentificationId);
		};
	}
	
	private Specification<PersonaJuridica> getSpecificationByToken(){
		String tokenSha1 = HashUtils.getHash(token);
		return (root, query, cb) -> cb.equal(root.<String>get("token"), tokenSha1);
	}
	
	private Specification<PersonaJuridica> getSpecificationByActivo(){
		return (root, query, cb) -> cb.equal(root.<Boolean>get("activo"), activo);
	}
	
	private Specification<PersonaJuridica> getSpecificationByNacionalidadId(){
		return (root, query, cb) -> {
			return cb.equal(root.<Pais>get("nacionalidad").<Long>get("id"), nacionalidadId);
		};
	}
	
	private Specification<PersonaJuridica> getSpecificationByProvinciaId(){
		return (root, query, cb) -> {
			return cb.equal(root.<Provincia>get("provincia").<Long>get("id"), provinciaId);
		};
	}
	
	private Specification<PersonaJuridica> getSpecificationByRazonSocialLike(){
		return (root, query, cb) -> cb.like(
				cb.lower(root.<String>get("razonSocial")),
				LIKE + razonSocialLike.toLowerCase() + LIKE);
	}
	
	private Specification<PersonaJuridica> getSpecificationByRepresentanteId(){
		return (root, query, cb) -> {
			return cb.equal(root.<PersonaFisica>get("representante").<Long>get("id"), representanteId);
		};
	}
	
	@Override
	public void reset() {
		this.id = null;
		this.numeroIdentificacion = null;
		this.numeroIdentificacionLike = null;
		this.tipoIdentificationId = null;
		this.token = null;
		this.activo = null;
		this.nacionalidadId = null;
		this.provinciaId = null;
		this.razonSocialLike = null;
		this.representanteId = null;
	}
	
}

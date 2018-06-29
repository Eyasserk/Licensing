package es.etsit.silcam.filter;

import org.springframework.data.jpa.domain.Specification;

import es.etsit.silcam.core.AbstractPageableFilter;
import es.etsit.silcam.entity.Pais;
import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.entity.TipoIdentificacion;
import es.etsit.silcam.util.HashUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonaFisicaFilter extends AbstractPageableFilter<PersonaFisica>{
	
	private static final String LIKE = "%";
	
	private Long id;
	private String numeroIdentificacion;
	private String numeroIdentificacionLike;
	private Long tipoIdentificationId;
	private String token;
	private Boolean activo;
	private Long nacionalidadId;
	private Long provinciaId;
	private String nombreCompletoLike;
	
	@Override
	public Specification<PersonaFisica> getSpecifications() {
		Specification<PersonaFisica> specById = null;
		Specification<PersonaFisica> specByNumeroIdentificacion = null;
		Specification<PersonaFisica> specByNumeroIdentificacionLike = null;
		Specification<PersonaFisica> specByTipoIdentificationId = null;
		Specification<PersonaFisica> specByToken = null;
		Specification<PersonaFisica> specByActivo = null;
		Specification<PersonaFisica> specByNacionalidadId = null;
		Specification<PersonaFisica> specByProvinciaId = null;
		Specification<PersonaFisica> specByNombreCompletoLike = null;
		
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
		if(nombreCompletoLike != null) {
			specByNombreCompletoLike = getSpecificationByNombreCompleltoLike();
		}
		return Specification.where(specById)
							.and(specByNumeroIdentificacion)
							.and(specByNumeroIdentificacionLike)
							.and(specByTipoIdentificationId)
							.and(specByToken)
							.and(specByActivo)
							.and(specByNacionalidadId)
							.and(specByProvinciaId)
							.and(specByNombreCompletoLike);
	}
	
	private Specification<PersonaFisica> getSpecificationById(){
		return (root, query, cb) -> cb.equal(root.<Long>get("id"), id);
	}
	
	private Specification<PersonaFisica> getSpecificationByNumeroIdentificacion(){
		return (root, query, cb) -> cb.equal(cb.lower(root.<String>get("numeroIdentificacion")), numeroIdentificacion.toLowerCase());
	}
	
	private Specification<PersonaFisica> getSpecificationByNumeroIdentificacionLike(){
		return (root, query, cb) -> cb.like(
				cb.lower(root.<String>get("numeroIdentificacion")),
				LIKE + numeroIdentificacionLike.toLowerCase() + LIKE);
	}
	
	private Specification<PersonaFisica> getSpecificationByTipoIdentificationId(){
		return (root, query, cb) -> {
			return cb.equal(root.<TipoIdentificacion>get("tipoIdentificacion").<Long>get("id"), tipoIdentificationId);
		};
	}
	
	private Specification<PersonaFisica> getSpecificationByToken(){
		String tokenSha1 = HashUtils.getHash(token);
		return (root, query, cb) -> cb.equal(root.<String>get("token"), tokenSha1);
	}
	
	private Specification<PersonaFisica> getSpecificationByActivo(){
		return (root, query, cb) -> cb.equal(root.<Boolean>get("activo"), activo);
	}
	
	private Specification<PersonaFisica> getSpecificationByNacionalidadId(){
		return (root, query, cb) -> {
			return cb.equal(root.<Pais>get("nacionalidad").<Long>get("id"), nacionalidadId);
		};
	}
	
	private Specification<PersonaFisica> getSpecificationByProvinciaId(){
		return (root, query, cb) -> {
			return cb.equal(root.<Provincia>get("provincia").<Long>get("id"), provinciaId);
		};
	}
	
	private Specification<PersonaFisica> getSpecificationByNombreCompleltoLike(){
		return (root, query, cb) -> cb.like(
				cb.lower(cb.concat(root.<String>get("nombre"), cb.concat(root.<String>get("apellido1"), root.<String>get("apellido2")))),
				LIKE + nombreCompletoLike.trim().toLowerCase() + LIKE);
		
		
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
		this.nombreCompletoLike = null;
	}
	
}

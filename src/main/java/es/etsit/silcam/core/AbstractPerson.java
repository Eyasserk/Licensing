package es.etsit.silcam.core;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import es.etsit.silcam.entity.Pais;
import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.entity.TipoIdentificacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AbstractPerson extends AbstractMasterEntity{
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=TipoIdentificacion.class)
	private TipoIdentificacion tipoIdentificacion;
	
	@Column(name="numeroIdentificacion", length=10, nullable=false, unique=true)
	private String numeroIdentificacion;
	
	@Column(name="direccion", length=255, nullable=false)
	private String direccionResidencia;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Provincia.class)
	private Provincia provincia;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Pais.class)
	private Pais nacionalidad;
	
	@Column(name="token", length=40, nullable=false)
	private String token;
	
	@Column(name="activo", nullable=false)
	private boolean activo;
	
}

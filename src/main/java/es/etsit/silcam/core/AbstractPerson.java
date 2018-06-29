package es.etsit.silcam.core;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import es.etsit.silcam.entity.Pais;
import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.entity.TipoIdentificacion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@MappedSuperclass
@ToString
public class AbstractPerson extends AbstractEntity{
	
	@ManyToOne(optional=false)
	@JoinColumn(name="tipo_identificacion_id")
	private TipoIdentificacion tipoIdentificacion;
	
	@Column(name="numeroIdentificacion", length=10, nullable=false, unique=true)
	private String numeroIdentificacion;
	
	@Column(name="email", length=100, nullable=false, unique=true)
	private String email;
	
	@Column(name="telefono", length=20, nullable=true, unique=false)
	private String telefono;
	
	@Column(name="direccion", length=255, nullable=false)
	private String direccionResidencia;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="provincia_id")
	private Provincia provincia;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="nacionalidad_id")
	private Pais nacionalidad;
	
	/**
	 * MD5 hash de la contraseña
	 */
	@Column(name="token", length=32, nullable=false)
	private String token;
	
	@Column(name="activo", nullable=false)
	private boolean activo;
	
}

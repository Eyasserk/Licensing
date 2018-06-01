package es.etsit.silcam.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="PersonaFisica", schema="poblacion")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersonaFisica {

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private long id;
	
	@Column(name="nombre", length=40, nullable=false)
	private String nombre;
	
	@Column(name="apellido1", length=40, nullable=false)
	private String apellido1;
	
	@Column(name="apellido2", length=40)
	private String apellido2;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=TipoIdentificacion.class)
	private TipoIdentificacion tipoIdentificacion;
	
	@Column(name="numeroIdentificacion", length=10, nullable=false, unique=true)
	private String numeroIdentificacion;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Pais.class)
	private Pais nacionalidad;
	
	@Column(name="fechaNacimiento", nullable=false)
	private Date fechaNacimiento;
	
	@Column(name="direccionResidencia", length=255, nullable=false)
	private String direccionResidencia;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Provincia.class)
	private Provincia provinciaResidencia;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Pais.class)
	private Pais paisResidencia;
	
	@Column(name="activo", nullable=false)
	private boolean activo;
	
}

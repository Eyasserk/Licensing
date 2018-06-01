package es.etsit.silcam.entity;

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
@Table(name="PersonaJuridica", schema="poblacion")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersonaJuridica{

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private long id;
	
	@Column(name="razonSocial", length=100, nullable=false, unique=true)
	private String razonSocial;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=PersonaFisica.class)
	private PersonaFisica representante;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=TipoIdentificacion.class)
	private TipoIdentificacion tipoIdentificacion;
	
	@Column(name="numeroIdentificacion", length=12, nullable=false, unique=true)
	private String numeroIdentificacion;
	
	@Column(name="direccionFiscal", length=255, nullable=false)
	private String direccionFiscal;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Pais.class)
	private Pais paisFiscal;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Pais.class)
	private Pais paisNacionalidad;
	
	@Column(name="activo", nullable=false)
	private boolean activo;
	
}

package es.etsit.silcam.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.etsit.silcam.entity.gis.Parcela;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Expediente", schema="concesion")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Expediente {

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="numeroExpediente", nullable=false, length=40)
	private String numeroExpediente;
	
	@Column(name="fechaInicio", nullable=false)
	private Date fechaInicio;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=TipoPersona.class)
	private TipoPersona tipoSolicitante;
	
	@Column(name="idSolicitante", nullable=false)
	private long idSolicitante;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=EstadoSolicitud.class)
	private EstadoSolicitud estado;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=FaseExpediente.class)
	private FaseExpediente fase;
	
	@ManyToMany(fetch=FetchType.EAGER, targetEntity=Mineral.class, cascade= CascadeType.ALL)
	@JoinTable(schema="concesion")
	private List<Mineral> minerales;
	
	@Transient
	private List<Parcela> parcelas;
	
	/**
	 * Provincias
	 */
	@Transient
	private List<Provincia> provincias;
	
	/**
	 * area en hectareas
	 */
	@Column(name="area", nullable=false)
	private double area;
}

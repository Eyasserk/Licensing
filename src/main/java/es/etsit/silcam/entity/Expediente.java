package es.etsit.silcam.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import es.etsit.silcam.entity.gis.Parcela;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Expediente", schema="concesion")
@Getter
@Setter
@ToString
public class Expediente {

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="numeroExpediente", nullable=false, length=40)
	private String numeroExpediente;
	
	@Column(name="fechaInicioExpediente", nullable=false)
	private Date fechaInicioExpediente;
	
	@Column(name="fechaInicioActividad", nullable=false)
	private Date fechaInicioActividad;
	
	@Column(name="fechaFinActividad", nullable=false)
	private Date fechaFinActividad;
	
	@Column(name="idSolicitante", nullable=false)
	private long idSolicitante;
	
	@Column(name="area", nullable=false)
	private double area;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipo_persona_id")
	private TipoPersona tipoSolicitante;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="estado_solicitud_id")
	private EstadoSolicitud estado;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fase_expediente_id")
	private FaseExpediente fase;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipo_expediente_id")
	private TipoExpediente tipoExpediente;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipo_solicitud_id")
	private TipoSolicitud tipoSolicitud;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(schema="concesion")
	private List<Mineral> minerales;
	
	@Transient
	private List<Parcela> parcelas;
	
	@Transient
	private List<Provincia> provincias;

}

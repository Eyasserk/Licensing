package es.etsit.silcam.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Expediente implements Serializable{

	private static final long serialVersionUID = -427877668228168792L;

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
	
	@ManyToOne(optional = false)
	@JoinColumn(name="provincia_id")
	private Provincia provincia;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="tipo_persona_id")
	private TipoPersona tipoSolicitante;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="estado_solicitud_id")
	private EstadoSolicitud estado;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="fase_expediente_id")
	private FaseExpediente fase;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="tipo_expediente_id")
	private TipoExpediente tipoExpediente;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="tipo_solicitud_id")
	private TipoSolicitud tipoSolicitud;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="mineral_id")
	private Mineral mineral;
	
	@Transient
	private Parcela parcela;

}

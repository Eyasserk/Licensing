package es.etsit.silcam.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.etsit.silcam.core.AbstractPerson;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="PersonaJuridica", schema="poblacion")
@Getter
@Setter
@ToString
public class PersonaJuridica extends AbstractPerson{
	
	@Column(name="razonSocial", length=100, nullable=false, unique=true)
	private String razonSocial;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=PersonaFisica.class)
	private PersonaFisica representante;
	
	@Column(name="fechaConstitucion", nullable=false)
	private Date fechaConstitucion;
	
}

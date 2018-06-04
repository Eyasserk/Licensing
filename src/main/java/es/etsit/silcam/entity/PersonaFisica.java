package es.etsit.silcam.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import es.etsit.silcam.core.AbstractPerson;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="PersonaFisica", schema="poblacion")
@Getter
@Setter
@ToString
public class PersonaFisica extends AbstractPerson{
	
	@Column(name="nombre", length=40, nullable=false)
	private String nombre;
	
	@Column(name="apellido1", length=40, nullable=false)
	private String apellido1;
	
	@Column(name="apellido2", length=40)
	private String apellido2;
	
	@Column(name="fechaNacimiento", nullable=false)
	private Date fechaNacimiento;
	
}

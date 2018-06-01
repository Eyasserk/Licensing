package es.etsit.silcam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="TipoIdentificacion", schema="poblacion")
@Getter
@Setter
@ToString
public class TipoIdentificacion {

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private long id;
	
	@Column(name="nombre", length=10, nullable=false, unique=true)
	private String nombre;
	
	@Column(name="descripcion", length=255)
	private String descripcion;
	
}

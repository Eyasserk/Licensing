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
@Table(name="FaseExpediente", schema="concesion")
@Getter
@Setter
@ToString
public class FaseExpediente {
	
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private long id;
	
	@Column(name="nombre", length=20, nullable=false)
	private String nombre;
	
	@Column(name="descripcion", length=255)
	private String descripcion;
	
}

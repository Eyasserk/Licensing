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
@Table(name="Pais", schema="poblacion")
@Getter
@Setter
@ToString
public class Pais {

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private long id;
	
	@Column(name="codigo", nullable=false)
	private int codigo;
	
	@Column(name="iso",length=3, nullable=false)
	private String iso;
	
	@Column(name="nombre", length=40, nullable=false)
	private String nombre;
}

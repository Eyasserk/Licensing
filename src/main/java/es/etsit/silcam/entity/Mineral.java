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
@Table(name="Mineral", schema="recursos")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mineral {

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private long id;
	
	@Column(name="nombre", length=20, nullable=false)
	private String nombre;
	
	@Column(name="descripcion", length=255)
	private String descripcion;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=GrupoMineral.class)
	private GrupoMineral grupo;
	
}

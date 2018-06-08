package es.etsit.silcam.core;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class AbstractMasterEntity extends AbstractEntity{

	@Column(name="codigo", length=3, nullable=false)
	private String codigo;
	
	@Column(name="nombre", length=40, nullable=false)
	private String nombre;
	
	@Column(name="descripcion", length=255)
	private String descripcion;
}

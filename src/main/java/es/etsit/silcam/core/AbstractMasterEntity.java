package es.etsit.silcam.core;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AbstractMasterEntity extends AbstractEntity{

	@Column(name="codigo", length=10, nullable=false)
	private String codigo;
	
	@Column(name="nombre", length=20, nullable=false)
	private String nombre;
	
	@Column(name="descripcion", length=255)
	private String descripcion;
}

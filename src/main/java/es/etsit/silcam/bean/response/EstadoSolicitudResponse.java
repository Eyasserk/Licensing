package es.etsit.silcam.bean.response;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoSolicitudResponse {

	@ApiModelProperty(value = "Unique ID")
	private long id;
	
	@ApiModelProperty(value = "Name")
	private String nombre;
	
	@ApiModelProperty(value = "Description")
	private String descripcion;
}

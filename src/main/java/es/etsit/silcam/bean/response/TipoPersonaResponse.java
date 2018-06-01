package es.etsit.silcam.bean.response;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class TipoPersonaResponse {

	@ApiModelProperty(value = "Unique ID")
	private long id;
	
	@ApiModelProperty(value = "Name")
	private String nombre;
	
	@ApiModelProperty(value = "Description")
	private String descripcion;
	
}

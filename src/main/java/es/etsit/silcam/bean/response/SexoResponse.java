package es.etsit.silcam.bean.response;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SexoResponse {

	@ApiModelProperty(value = "Unique ID")
	private long id;
	
	@ApiModelProperty(value = "Code")
	private String codigo;
	
	@ApiModelProperty(value = "Name")
	private String nombre;
}

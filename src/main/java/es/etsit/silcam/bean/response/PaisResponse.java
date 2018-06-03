package es.etsit.silcam.bean.response;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaisResponse {
	
	@ApiModelProperty(value = "Unique ID")
	private long id;
	
	@ApiModelProperty(value = "Country code")
	private String codigo;
	
	@ApiModelProperty(value = "ISO")
	private int iso;
	
	@ApiModelProperty(value = "Name")
	private String nombre;
	
}

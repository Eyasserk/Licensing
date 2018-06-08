package es.etsit.silcam.bean.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaisResponse  implements Serializable{
	
	private static final long serialVersionUID = -2238730744980613258L;

	@ApiModelProperty(value = "Unique ID")
	private long id;
	
	@ApiModelProperty(value = "Country code")
	private String codigo;
	
	@ApiModelProperty(value = "ISO")
	private int iso;
	
	@ApiModelProperty(value = "Name")
	private String nombre;
	
}

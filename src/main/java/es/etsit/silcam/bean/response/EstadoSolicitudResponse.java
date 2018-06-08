package es.etsit.silcam.bean.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoSolicitudResponse  implements Serializable{

	private static final long serialVersionUID = -4449649036606796440L;

	@ApiModelProperty(value = "Unique ID")
	private long id;
	
	@ApiModelProperty(value = "Code")
	private String codigo;
	
	@ApiModelProperty(value = "Name")
	private String nombre;
	
	@ApiModelProperty(value = "Description")
	private String descripcion;
}

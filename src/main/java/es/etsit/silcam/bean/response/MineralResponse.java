package es.etsit.silcam.bean.response;

import com.wordnik.swagger.annotations.ApiModelProperty;

import es.etsit.silcam.entity.GrupoMineral;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MineralResponse {

	@ApiModelProperty(value = "Unique ID")
	private long id;
	
	@ApiModelProperty(value = "Code")
	private String codigo;
	
	@ApiModelProperty(value = "Name")
	private String nombre;
	
	@ApiModelProperty(value = "Description")
	private String descripcion;
	
	@ApiModelProperty(value = "Group")
	private GrupoMineral grupo;
}

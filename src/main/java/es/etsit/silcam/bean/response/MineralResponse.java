package es.etsit.silcam.bean.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.etsit.silcam.entity.GrupoMineral;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MineralResponse  implements Serializable{

	private static final long serialVersionUID = -1041738917391899133L;

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

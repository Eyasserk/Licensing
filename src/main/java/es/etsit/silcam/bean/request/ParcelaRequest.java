package es.etsit.silcam.bean.request;

import java.util.List;

import es.etsit.silcam.util.Coordenada;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelaRequest {
	
	@ApiModelProperty(value = "Case ID")
	private long expedienteId;
	
	@ApiModelProperty(value = "Coordinates")
	private List<Coordenada> coordenadas;
	
	@ApiModelProperty(value = "Province ID")
	private long provinciaId;
	
	@ApiModelProperty(value = "Area")
	private double area;
}

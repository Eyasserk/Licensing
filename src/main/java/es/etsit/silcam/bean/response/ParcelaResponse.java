package es.etsit.silcam.bean.response;

import java.util.List;

import es.etsit.silcam.util.Coordenada;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelaResponse {

	@ApiModelProperty(value = "Unique ID")
    private String id;
	
	@ApiModelProperty(value = "Case ID")
	private long expedienteId;
	
	@ApiModelProperty(value = "Coordinates")
	private List<Coordenada> coordenadas;
	
}

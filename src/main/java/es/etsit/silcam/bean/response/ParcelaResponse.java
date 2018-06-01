package es.etsit.silcam.bean.response;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModelProperty;

import es.etsit.silcam.util.Coordenada;
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

package es.etsit.silcam.bean.response;

import java.io.Serializable;
import java.util.List;

import es.etsit.silcam.util.Coordenada;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelaResponse implements Serializable{

	private static final long serialVersionUID = 582137383729545078L;

	@ApiModelProperty(value = "Unique ID")
    private String id;
	
	@ApiModelProperty(value = "Case ID")
	private long expedienteId;
	
	@ApiModelProperty(value = "Coordinates")
	private List<Coordenada> coordenadas;
	
}

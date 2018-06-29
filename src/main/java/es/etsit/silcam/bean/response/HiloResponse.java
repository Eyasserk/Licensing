package es.etsit.silcam.bean.response;

import java.util.List;

import es.etsit.silcam.util.Mensaje;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HiloResponse {
	
	@ApiModelProperty("Thread ID")
	private String id;
	
	@ApiModelProperty("Thread Title")
	private String titulo;
	
	@ApiModelProperty("Case ID")
	private long idExpediente;
	
	@ApiModelProperty("Messages")
	private List<Mensaje> mensajes;
}

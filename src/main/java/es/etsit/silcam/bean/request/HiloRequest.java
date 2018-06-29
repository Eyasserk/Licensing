package es.etsit.silcam.bean.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HiloRequest {
	
	@NotBlank
	@ApiModelProperty("Thread title")
	private String titulo;
	
	@Min(value=1)
	@ApiModelProperty("Case ID")
	private long idExpediente;
	
	@NotNull
	@ApiModelProperty("Message")
	private MensajeRequest mensaje;
}

package es.etsit.silcam.bean.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MensajeRequest {
	
	@NotNull
	@ApiModelProperty("Sender Type. 1 Silcam - 2 User")
	private int senderType;
	
	@NotBlank
	@ApiModelProperty("Message Content")
	private String contenido;
}

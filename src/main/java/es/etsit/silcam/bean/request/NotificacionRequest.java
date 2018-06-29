package es.etsit.silcam.bean.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificacionRequest {
	
	@Min(1)
	@Max(2)
	@ApiModelProperty(value = "Notification Type ID")
	private int type;
	
	@Min(1)
	@ApiModelProperty(value = "Case ID")
	private long idExpediente;
	
	@NotBlank
	@ApiModelProperty(value = "Title")
	private String titulo;
	
	@NotBlank
	@ApiModelProperty(value = "Description")
	private String descripcion;
}

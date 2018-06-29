package es.etsit.silcam.bean.response;

import java.util.Date;

import es.etsit.silcam.util.NotificationType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificacionResponse {

	@ApiModelProperty(value = "Notification ID")
	private String id;
	
	@ApiModelProperty(value = "Notification state 0 read - 1 unread")
	private int state;
	
	@ApiModelProperty(value = "Notification Type")
	private NotificationType type;
	
	@ApiModelProperty(value = "Person ID")
	private long idPersona;
	
	@ApiModelProperty(value = "Case ID")
	private long idExpediente;
	
	@ApiModelProperty(value = "Case Number")
	private String numeroExpediente;
	
	@ApiModelProperty(value = "Title")
	private String titulo;
	
	@ApiModelProperty(value = "Description")
	private String descripcion;
	
	@ApiModelProperty(value = "Date")
	private Date fecha;
}

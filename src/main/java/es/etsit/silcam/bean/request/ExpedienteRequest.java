package es.etsit.silcam.bean.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExpedienteRequest {
	
	@ApiModelProperty(value = "Mineral")
	private Long mineral;
	
	@ApiModelProperty(value = "Parcel")
	private ParcelaRequest parcela;
	
	@ApiModelProperty(value = "Tipo persona solicitante (fisica/juridica) ")
	private long tipoPersonaSolicitante;
	
	@ApiModelProperty(value = "ID persona")
	private long idPersonaSolicitante;
	
	@ApiModelProperty(value = "Tipo de Solicitud")
	private long idTipoSolicitud;
	
	@ApiModelProperty(value = "Tipo de Expediente")
	private long idTipoExpediente;
	
	@ApiModelProperty(value = "Activity Start Date")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date activityStartDate;
	
	@ApiModelProperty(value = "Activity End Date")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date activityEndDate;
	
}

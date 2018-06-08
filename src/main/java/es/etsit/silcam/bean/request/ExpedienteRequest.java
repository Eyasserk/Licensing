package es.etsit.silcam.bean.request;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
	@Min(value=1)
	private Long mineral;
	
	@ApiModelProperty(value = "Parcel")
	@NotNull
	private ParcelaRequest parcela;
	
	@ApiModelProperty(value = "Tipo persona solicitante (fisica/juridica) ")
	@Min(value=1)
	private long tipoPersonaSolicitante;
	
	@ApiModelProperty(value = "ID persona")
	@Min(value=1)
	private long idPersonaSolicitante;
	
	@ApiModelProperty(value = "Tipo de Solicitud")
	@Min(value=1)
	private long idTipoSolicitud;
	
	@ApiModelProperty(value = "Tipo de Expediente")
	@Min(value=1)
	private long idTipoExpediente;
	
	@ApiModelProperty(value = "Activity Start Date")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private Date activityStartDate;
	
	@ApiModelProperty(value = "Activity End Date")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private Date activityEndDate;
	
}

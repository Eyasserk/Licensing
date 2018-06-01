package es.etsit.silcam.bean.request;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExpedienteRequest {
	
	@ApiModelProperty(value = "Minerals")
	private List<Long> minerales;
	
	@ApiModelProperty(value = "Parcels")
	private List<ParcelaRequest> parcelas;
	
	//@ApiModelProperty(value = "Tipo persona solicitante (fisica/juridica) ")
	//private long tipoPersonaSolicitante;
	
	//@ApiModelProperty(value = "ID persona")
	//private long idPersonaSolicitante;
	
}

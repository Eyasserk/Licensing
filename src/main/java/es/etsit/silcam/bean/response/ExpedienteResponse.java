package es.etsit.silcam.bean.response;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModelProperty;

import es.etsit.silcam.entity.EstadoSolicitud;
import es.etsit.silcam.entity.FaseExpediente;
import es.etsit.silcam.entity.Mineral;
import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.entity.gis.Parcela;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpedienteResponse {

	@ApiModelProperty(value = "Unique ID")
	private long id;
	
	@ApiModelProperty(value = "Case Number")
	private String numeroExpediente;
	
	@ApiModelProperty(value = "Initiation Date")
	private String fechaInicio;
	
	@ApiModelProperty(value = "State")
	private EstadoSolicitud estado;
	
	@ApiModelProperty(value = "Phase")
	private FaseExpediente fase;
	
	@ApiModelProperty(value = "Minerals")
	private List<Mineral> minerales;
	
	@ApiModelProperty(value = "Parcels")
	private List<Parcela> parcelas;
	
	@ApiModelProperty(value = "Provinces")
	private List<Provincia> provincias;
	
	@ApiModelProperty(value = "Area in HR")
	private double area;
}

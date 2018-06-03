package es.etsit.silcam.bean.response;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModelProperty;

import es.etsit.silcam.entity.EstadoSolicitud;
import es.etsit.silcam.entity.FaseExpediente;
import es.etsit.silcam.entity.Mineral;
import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.entity.TipoExpediente;
import es.etsit.silcam.entity.TipoSolicitud;
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
	private String fechaInicioExpediente;
	
	@ApiModelProperty(value = "Activity Initiation Date")
	private String fechaInicioActividad;
	
	@ApiModelProperty(value = "Activity End Date")
	private String fechaFinActividad;
	
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
	
	@ApiModelProperty(value = "Requester Identification Number")
	private String numeroIdentificacionSolicitante;
	
	@ApiModelProperty(value = "Request name / company name")
	private String nombreSolicitante;
	
	@ApiModelProperty(value = "Request Type")
	private TipoSolicitud tipoSolicitud;
	
	@ApiModelProperty(value = "Case Type")
	private TipoExpediente tipoExpediente;
	
}

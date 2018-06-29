package es.etsit.silcam.bean.response;

import java.io.Serializable;

import es.etsit.silcam.entity.PersonaFisica;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaJuridicaResponse  implements Serializable{

	private static final long serialVersionUID = -2589839400343943158L;

	@ApiModelProperty(value = "Unique ID")
	private long id;
	
	@ApiModelProperty(value = "Business Name")
	private String razonSocial;
	
	@ApiModelProperty(value = "Representative")
	private PersonaFisica representante;
	
	@ApiModelProperty(value = "Constitution Date")
	private String fechaConstitucion;
	
	@ApiModelProperty(value = "ID Type")
	private String tipoIdentificacion;
	
	@ApiModelProperty(value = "ID Number")
	private String numeroIdentificacion;
	
	@ApiModelProperty(value = "Email")
	private String email;
	
	@ApiModelProperty(value = "Phone Number")
	private String telefono;
	
	@ApiModelProperty(value = "Fiscal Address")
	private String direccionFiscal;
	
	@ApiModelProperty(value = "Fiscal Province")
	private String provinciaFisical;
	
	@ApiModelProperty(value = "Nationality")
	private String paisNacionalidad;
	
}

package es.etsit.silcam.bean.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaFisicaResponse  implements Serializable{

	private static final long serialVersionUID = 3696225412350004305L;

	@ApiModelProperty(value = "Unique ID")
	private long id;
	
	@ApiModelProperty(value = "Name")
	private String nombre;
	
	@ApiModelProperty(value = "Last Name 1")
	private String apellido1;
	
	@ApiModelProperty(value = "Last Name 2")
	private String apellido2;
	
	@ApiModelProperty(value = "ID Type")
	private String tipoIdentificacion;
	
	@ApiModelProperty(value = "ID Number")
	private String numeroIdentificacion;
	
	@ApiModelProperty(value = "Nationality")
	private String nacionalidad;
	
	@ApiModelProperty(value = "Birth Date")
	private String fechaNacimiento;
	
	@ApiModelProperty(value = "Address")
	private String direccionResidencia;
	
	@ApiModelProperty(value = "Residence Province")
	private String provinciaResidencia;
	
	@ApiModelProperty(value = "Residence Country")
	private String paisResidencia;
	
}

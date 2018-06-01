package es.etsit.silcam.bean.response;

import com.wordnik.swagger.annotations.ApiModelProperty;

import es.etsit.silcam.entity.PersonaFisica;

public class PersonaJuridicaResponse {

	@ApiModelProperty(value = "Unique ID")
	private long id;
	
	@ApiModelProperty(value = "Business Name")
	private String razonSocial;
	
	@ApiModelProperty(value = "Representative")
	private PersonaFisica representante;
	
	@ApiModelProperty(value = "ID Type")
	private String tipoIdentificacion;
	
	@ApiModelProperty(value = "ID Number")
	private String numeroIdentificacion;
	
	@ApiModelProperty(value = "Fiscal Address")
	private String direccionFiscal;
	
	@ApiModelProperty(value = "Fiscal Country")
	private String paisFiscal;
	
	@ApiModelProperty(value = "Nationality")
	private String paisNacionalidad;
	
}

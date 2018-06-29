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
public class AuthenticationRequest {

	@Min(1)
	@Max(2)
	@ApiModelProperty( value = "Authentication Type Id")
	private int id;
	
	@NotBlank
	@ApiModelProperty(value = "NIF/ Case Number")
	private String number;
	
	@ApiModelProperty(value = "Person Type ID")
	private long personType;
	
	@NotBlank
	@ApiModelProperty(value = "Key")
	private String clave;
	
}

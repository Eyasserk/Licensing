package es.etsit.silcam.bean.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthenticationResponse implements Serializable{

	private static final long serialVersionUID = -6807504510921663975L;
	
	@ApiModelProperty(value = "Token")
	private String token;
	
	@ApiModelProperty(value = "Person ID")
	private long userId;
	
	@ApiModelProperty(value = "Person Type ID")
	private long userTypeId;
	
}

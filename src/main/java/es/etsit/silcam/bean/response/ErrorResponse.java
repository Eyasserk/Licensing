package es.etsit.silcam.bean.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse  implements Serializable{

	private static final long serialVersionUID = -5463880053411313611L;

	@ApiModelProperty(value = "The mandatory code carried by the api response.")
	private int code;

	@ApiModelProperty(value = "Optional message supplementing the code.")
	private String message;

}

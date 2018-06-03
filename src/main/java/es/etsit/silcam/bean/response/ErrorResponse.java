package es.etsit.silcam.bean.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	@ApiModelProperty(value = "The mandatory code carried by the api response.")
	private int code;

	@ApiModelProperty(value = "Optional message supplementing the code.")
	private String message;

}

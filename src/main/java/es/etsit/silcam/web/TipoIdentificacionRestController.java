package es.etsit.silcam.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.etsit.silcam.bean.response.ErrorResponse;
import es.etsit.silcam.bean.response.TipoIdentificacionResponse;
import es.etsit.silcam.entity.TipoIdentificacion;
import es.etsit.silcam.service.TipoIdentificacionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(value = "Silcam")
@RestController
@RequestMapping(path = "/api/1")
@Slf4j
public class TipoIdentificacionRestController {

	private TipoIdentificacionService tipoIdentificacionService;
	
	@Autowired
	public void setTipoIdentificacionService(TipoIdentificacionService tipoIdentificacionService) {
		this.tipoIdentificacionService = tipoIdentificacionService;
	}
	
	/**
	 * findAll
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/tiposIdentificacion", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de los tipos de identificacion posibles",
			notes = "Devuelve la lista de los tipos de identificacion posibles")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = TipoIdentificacionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<TipoIdentificacionResponse>> findAll(){
		log.info("Request tipos solicitud");
		return new ResponseEntity<List<TipoIdentificacionResponse>>(parse(tipoIdentificacionService.findAll()),HttpStatus.OK);
	}
	
	private TipoIdentificacionResponse parse(TipoIdentificacion tipo) {
		TipoIdentificacionResponse response =null;
		if(tipo != null) {
			response = new TipoIdentificacionResponse();
			response.setDescripcion(tipo.getDescripcion());
			response.setId(tipo.getId());
			response.setNombre(tipo.getNombre());
			response.setCodigo(tipo.getCodigo());
		}
		return response;
	}
	
	private List<TipoIdentificacionResponse> parse(List<TipoIdentificacion> list){
		List<TipoIdentificacionResponse> response = null;
		if(list != null) {
			response = new ArrayList<TipoIdentificacionResponse>();
			for(TipoIdentificacion tipo : list) {
				response.add(parse(tipo));
			}
		}
		return response;
	}
	
}

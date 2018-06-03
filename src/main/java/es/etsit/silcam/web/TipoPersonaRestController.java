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
import es.etsit.silcam.bean.response.TipoPersonaResponse;
import es.etsit.silcam.entity.TipoPersona;
import es.etsit.silcam.service.TipoPersonaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(value = "Silcam")
@RestController
@RequestMapping(path = "/api/1")
@Slf4j
public class TipoPersonaRestController {

	private TipoPersonaService tipoPersonaService;
	
	@Autowired
	public void setTipoPersonaService(TipoPersonaService tipoPersonaService) {
		this.tipoPersonaService = tipoPersonaService;
	}
	
	/**
	 * findAll
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/tiposPersona", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de los tipos de persona posibles",
			notes = "Devuelve la lista de los tipos de persona posibles")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = TipoPersonaResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<TipoPersonaResponse>> findAll(){
		log.info("Request tipos solicitud");
		return new ResponseEntity<List<TipoPersonaResponse>>(parse(tipoPersonaService.findAll()),HttpStatus.OK);
	}
	
	private TipoPersonaResponse parse(TipoPersona tipo) {
		TipoPersonaResponse response =null;
		if(tipo != null) {
			response = new TipoPersonaResponse();
			response.setDescripcion(tipo.getDescripcion());
			response.setId(tipo.getId());
			response.setNombre(tipo.getNombre());
			response.setCodigo(tipo.getCodigo());
		}
		return response;
	}
	
	private List<TipoPersonaResponse> parse(List<TipoPersona> list){
		List<TipoPersonaResponse> response = null;
		if(list != null) {
			response = new ArrayList<TipoPersonaResponse>();
			for(TipoPersona tipo : list) {
				response.add(parse(tipo));
			}
		}
		return response;
	}
	
}

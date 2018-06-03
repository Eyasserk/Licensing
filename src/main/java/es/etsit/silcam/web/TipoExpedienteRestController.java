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

import com.wordnik.swagger.annotations.Api;

import es.etsit.silcam.bean.response.ErrorResponse;
import es.etsit.silcam.bean.response.TipoExpedienteResponse;
import es.etsit.silcam.entity.TipoExpediente;
import es.etsit.silcam.service.TipoExpedienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(value = "Silcam")
@RestController
@RequestMapping(path = "/api/1")
@Slf4j
public class TipoExpedienteRestController {

	private TipoExpedienteService tipoExpedienteService;
	
	@Autowired
	public void setTipoExpedienteService(TipoExpedienteService tipoExpedienteService) {
		this.tipoExpedienteService = tipoExpedienteService;
	}
	
	/**
	 * findAll
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/tiposExpediente", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de los tipos de expediente posibles",
			notes = "Devuelve la lista de los tipos de expediente posibles")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = TipoExpedienteResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<TipoExpedienteResponse>> findAll(){
		log.info("Request tipos solicitud");
		return new ResponseEntity<List<TipoExpedienteResponse>>(parse(tipoExpedienteService.findAll()),HttpStatus.OK);
	}
	
	private TipoExpedienteResponse parse(TipoExpediente tipo) {
		TipoExpedienteResponse response =null;
		if(tipo != null) {
			response = new TipoExpedienteResponse();
			response.setDescripcion(tipo.getDescripcion());
			response.setId(tipo.getId());
			response.setNombre(tipo.getNombre());
			response.setCodigo(tipo.getCodigo());
		}
		return response;
	}
	
	private List<TipoExpedienteResponse> parse(List<TipoExpediente> list){
		List<TipoExpedienteResponse> response = null;
		if(list != null) {
			response = new ArrayList<TipoExpedienteResponse>();
			for(TipoExpediente tipo : list) {
				response.add(parse(tipo));
			}
		}
		return response;
	}
	
}

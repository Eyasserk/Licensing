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
import es.etsit.silcam.bean.response.FaseExpedienteResponse;
import es.etsit.silcam.entity.FaseExpediente;
import es.etsit.silcam.service.FaseExpedienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(value = "Silcam")
@RestController
@RequestMapping(path = "/api/1")
@Slf4j
public class FaseExpedienteRestController {

	private FaseExpedienteService faseExpedienteService;
	
	@Autowired
	public void setFaseExpedienteService(FaseExpedienteService faseExpedienteService) {
		this.faseExpedienteService = faseExpedienteService;
	}
	
	/**
	 * findAll
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/fasesExpediente", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de las fases de expedientes posibles",
			notes = "Devuelve la lista de las fases de expedientes posibles")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = FaseExpedienteResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<FaseExpedienteResponse>> findAll(){
		log.info("Request estados solicitud");
		return new ResponseEntity<List<FaseExpedienteResponse>>(parse(faseExpedienteService.findAll()),HttpStatus.OK);
	}
	
	private FaseExpedienteResponse parse(FaseExpediente estado) {
		FaseExpedienteResponse response =null;
		if(estado != null) {
			response = new FaseExpedienteResponse();
			response.setDescripcion(estado.getDescripcion());
			response.setId(estado.getId());
			response.setNombre(estado.getNombre());
			response.setCodigo(estado.getCodigo());
		}
		return response;
	}
	
	private List<FaseExpedienteResponse> parse(List<FaseExpediente> list){
		List<FaseExpedienteResponse> response = null;
		if(list != null) {
			response = new ArrayList<FaseExpedienteResponse>();
			for(FaseExpediente estado : list) {
				response.add(parse(estado));
			}
		}
		return response;
	}
	
}

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
import es.etsit.silcam.bean.response.EstadoSolicitudResponse;
import es.etsit.silcam.entity.EstadoSolicitud;
import es.etsit.silcam.service.EstadoSolicitudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(value = "Silcam")
@RestController
@RequestMapping(path = "/api/1")
@Slf4j
public class EstadoSolicitudRestController {

	private EstadoSolicitudService estadoSolicitudService;
	
	@Autowired
	public void setEstadoSolicitudService(EstadoSolicitudService estadoSolicitudService) {
		this.estadoSolicitudService = estadoSolicitudService;
	}
	
	/**
	 * findAll
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/estadosSolicitud", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de los estados de solicitudes posibles",
			notes = "Devuelve la lista de los estados de solicitudes posibles")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = EstadoSolicitudResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<EstadoSolicitudResponse>> findAll(){
		log.info("Request estados solicitud");
		return new ResponseEntity<List<EstadoSolicitudResponse>>(parse(estadoSolicitudService.findAll()),HttpStatus.OK);
	}
	
	private EstadoSolicitudResponse parse(EstadoSolicitud estado) {
		EstadoSolicitudResponse response =null;
		if(estado != null) {
			response = new EstadoSolicitudResponse();
			response.setDescripcion(estado.getDescripcion());
			response.setId(estado.getId());
			response.setNombre(estado.getNombre());
			response.setCodigo(estado.getCodigo());
		}
		return response;
	}
	
	private List<EstadoSolicitudResponse> parse(List<EstadoSolicitud> list){
		List<EstadoSolicitudResponse> response = null;
		if(list != null) {
			response = new ArrayList<EstadoSolicitudResponse>();
			for(EstadoSolicitud estado : list) {
				response.add(parse(estado));
			}
		}
		return response;
	}
	
}

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
import es.etsit.silcam.bean.response.GrupoMineralResponse;
import es.etsit.silcam.entity.GrupoMineral;
import es.etsit.silcam.service.GrupoMineralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(value = "Silcam")
@RestController
@RequestMapping(path = "/api/1")
@Slf4j
public class GrupoMineralRestController {

	private GrupoMineralService grupoMineralService;
	
	@Autowired
	public void setGrupoMineralService(GrupoMineralService grupoMineralService) {
		this.grupoMineralService = grupoMineralService;
	}
	
	/**
	 * findAll
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/gruposMineral", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de los grupos de minerales posibles",
			notes = "Devuelve la lista de los grupos de minerales posibles")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = GrupoMineralResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<GrupoMineralResponse>> findAll(){
		log.info("Request estados solicitud");
		return new ResponseEntity<List<GrupoMineralResponse>>(parse(grupoMineralService.findAll()),HttpStatus.OK);
	}
	
	private GrupoMineralResponse parse(GrupoMineral estado) {
		GrupoMineralResponse response =null;
		if(estado != null) {
			response = new GrupoMineralResponse();
			response.setDescripcion(estado.getDescripcion());
			response.setId(estado.getId());
			response.setNombre(estado.getNombre());
			response.setCodigo(estado.getCodigo());
		}
		return response;
	}
	
	private List<GrupoMineralResponse> parse(List<GrupoMineral> list){
		List<GrupoMineralResponse> response = null;
		if(list != null) {
			response = new ArrayList<GrupoMineralResponse>();
			for(GrupoMineral estado : list) {
				response.add(parse(estado));
			}
		}
		return response;
	}
	
}

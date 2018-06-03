package es.etsit.silcam.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.etsit.silcam.bean.response.ErrorResponse;
import es.etsit.silcam.bean.response.MineralResponse;
import es.etsit.silcam.entity.Mineral;
import es.etsit.silcam.service.MineralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(value = "Silcam")
@RestController
@RequestMapping(path = "/api/1")
@Slf4j
public class MineralRestController {

	private MineralService mineralService;
	
	@Autowired
	public void setMineralService(MineralService mineralService) {
		this.mineralService = mineralService;
	}
	
	/**
	 * findAll
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/minerales", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de minerales posibles",
			notes = "Devuelve la lista de minerales posibles")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = MineralResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<MineralResponse>> findAll(){
		log.info("Request minerales");
		return new ResponseEntity<List<MineralResponse>>(parse(mineralService.findAll()),HttpStatus.OK);
	}
	
	/**
	 * find by grupo mineral
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/minerales/grupo/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de minerales posibles por grupo",
			notes = "Devuelve la lista de minerales posibles por grupo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = MineralResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<MineralResponse>> findAllByGrupo(
			@ApiParam(value = "Mineral Group ID", required = true) @PathVariable long grupoId
			){
		log.info("Request minerales por grupo");
		return new ResponseEntity<List<MineralResponse>>(parse(mineralService.findByGrupoId(grupoId)),HttpStatus.OK);
	}
	
	private MineralResponse parse(Mineral estado) {
		MineralResponse response =null;
		if(estado != null) {
			response = new MineralResponse();
			response.setDescripcion(estado.getDescripcion());
			response.setId(estado.getId());
			response.setNombre(estado.getNombre());
			response.setCodigo(estado.getCodigo());
			response.setGrupo(estado.getGrupo());
		}
		return response;
	}
	
	private List<MineralResponse> parse(List<Mineral> list){
		List<MineralResponse> response = null;
		if(list != null) {
			response = new ArrayList<MineralResponse>();
			for(Mineral estado : list) {
				response.add(parse(estado));
			}
		}
		return response;
	}
	
}

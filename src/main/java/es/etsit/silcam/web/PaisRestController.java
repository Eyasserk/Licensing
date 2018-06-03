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
import es.etsit.silcam.bean.response.PaisResponse;
import es.etsit.silcam.entity.Pais;
import es.etsit.silcam.service.PaisService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(value = "Silcam")
@RestController
@RequestMapping(path = "/api/1")
@Slf4j
public class PaisRestController {

	private PaisService paisService;
	
	@Autowired
	public void setPaisService(PaisService paisService) {
		this.paisService = paisService;
	}
	
	/**
	 * findAll
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/paises", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de paises",
			notes = "Devuelve la lista de paises")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = PaisResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<PaisResponse>> findAll(){
		log.info("Request estados solicitud");
		return new ResponseEntity<List<PaisResponse>>(parse(paisService.findAll()),HttpStatus.OK);
	}
	
	private PaisResponse parse(Pais estado) {
		PaisResponse response =null;
		if(estado != null) {
			response = new PaisResponse();
			response.setIso(estado.getIso());
			response.setId(estado.getId());
			response.setNombre(estado.getNombre());
			response.setCodigo(estado.getCodigo());
		}
		return response;
	}
	
	private List<PaisResponse> parse(List<Pais> list){
		List<PaisResponse> response = null;
		if(list != null) {
			response = new ArrayList<PaisResponse>();
			for(Pais estado : list) {
				response.add(parse(estado));
			}
		}
		return response;
	}
	
}

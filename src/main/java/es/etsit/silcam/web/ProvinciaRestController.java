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
import es.etsit.silcam.bean.response.ProvinciaResponse;
import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.service.ProvinciaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(value = "Silcam")
@RestController
@RequestMapping(path = "/api/1")
@Slf4j
public class ProvinciaRestController {

	private ProvinciaService provinciaService;
	
	@Autowired
	public void setProvinciaService(ProvinciaService provinciaService) {
		this.provinciaService = provinciaService;
	}
	
	/**
	 * findAll
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/provincias", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de provincias",
			notes = "Devuelve la lista de provincias")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ProvinciaResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<ProvinciaResponse>> findAll(){
		log.info("Request provincias solicitud");
		return new ResponseEntity<List<ProvinciaResponse>>(parse(provinciaService.findAll()),HttpStatus.OK);
	}
	
	private ProvinciaResponse parse(Provincia provincia) {
		ProvinciaResponse response =null;
		if(provincia != null) {
			response = new ProvinciaResponse();
			response.setId(provincia.getId());
			response.setNombre(provincia.getNombre());
			response.setCodigo(provincia.getCodigo());
		}
		return response;
	}
	
	private List<ProvinciaResponse> parse(List<Provincia> list){
		List<ProvinciaResponse> response = null;
		if(list != null) {
			response = new ArrayList<ProvinciaResponse>();
			for(Provincia provincia : list) {
				response.add(parse(provincia));
			}
		}
		return response;
	}
	
}

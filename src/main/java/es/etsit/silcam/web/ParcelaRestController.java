package es.etsit.silcam.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import es.etsit.silcam.bean.request.ParcelaRequest;
import es.etsit.silcam.bean.response.ErrorResponse;
import es.etsit.silcam.bean.response.ParcelaResponse;
import es.etsit.silcam.entity.gis.Parcela;
import es.etsit.silcam.service.ParcelaService;
import es.etsit.silcam.service.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

@Api(value = "Silcam")
@RestController
@RequestMapping(path = "/api/1")
@Slf4j
public class ParcelaRestController {

	private ParcelaService parcelaService;
	private ProvinciaService provinciaService;
	
	@Autowired
	private void setParcelaService(ParcelaService parcelaService) {
		this.parcelaService = parcelaService;
	}
	
	@Autowired
	public void setProvinciaService(ProvinciaService provinciaService) {
		this.provinciaService = provinciaService;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/parcelas", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de parcelas",
			notes = "Devuelve la lista de parcelas que corresponden con la query de busqueda")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ParcelaResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<ParcelaResponse>> find() {
		log.info("Request find expedientes");
		
		return new ResponseEntity<List<ParcelaResponse>>(parse(parcelaService.findAll()), HttpStatus.OK);
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/parcelas", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a parcela.", notes = "Creates a parcela.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = ParcelaResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 409, message = "Conflict", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<ParcelaResponse> create(@RequestBody @Valid ParcelaRequest body){
		log.info("Request create parcerla: {}", body);
		
		return new ResponseEntity<ParcelaResponse>(parse(parcelaService.create(parse(body))), HttpStatus.CREATED);
	}
	
	private Parcela parse(ParcelaRequest request) {
		Parcela parcela = null;
		if(request != null) {
			parcela = new Parcela();
			parcela.setCoordenadas(request.getCoordenadas());
			parcela.setExpedienteId(request.getExpedienteId());
			parcela.setProvincia(provinciaService.findById(request.getProvinciaId()));
		}
		return parcela;
	}
	
	private ParcelaResponse parse(Parcela parcela) {
		ParcelaResponse response = null;
		if(parcela != null) {
			response = new ParcelaResponse();
			response.setCoordenadas(parcela.getCoordenadas());
			response.setExpedienteId(parcela.getExpedienteId());
			response.setId(parcela.getId());
		}
		return response;
	}
	
	private List<ParcelaResponse> parse(List<Parcela> list){
		List<ParcelaResponse> response = new ArrayList<ParcelaResponse>();
		if(list != null) {
			for(Parcela parcela : list) {
				response.add(parse(parcela));
			}
		}
		return response;
	}
	
}

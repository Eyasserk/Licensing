package es.etsit.silcam.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.etsit.silcam.bean.response.ErrorResponse;
import es.etsit.silcam.bean.response.PersonaFisicaResponse;
import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.filter.PersonaFisicaFilter;
import es.etsit.silcam.service.PersonaFisicaService;
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
public class PersonaFisicaRestController {

	private PersonaFisicaService personaFisicaService;
	
	@Autowired
	public void setPersonaFisicaService(PersonaFisicaService personaFisicaService) {
		this.personaFisicaService = personaFisicaService;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/personasFisicas", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de personas fisicas",
			notes = "Devuelve la lista de personas fisicas que corresponden con la query de busqueda")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", responseContainer="list" ,response = PersonaFisicaResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<PersonaFisicaResponse>> findAll(
			@RequestParam(required = false) @ApiParam(value = "ID Number Like", name = "numeroIdentificacion",
			required = false) String numeroIdentificacion,
			@RequestParam(required = false) @ApiParam(value = "Identification Type ID", name = "tipoIdentificationId",
			required = false) Long tipoIdentificationId,
			@RequestParam(required = false) @ApiParam(value = "Token", name = "token",
			required = false) String token,
			@RequestParam(required = false) @ApiParam(value = "Active", name = "activo",
			required = false) Boolean activo,
			@RequestParam(required = false) @ApiParam(value = "Nationality - Country ID", name = "nacionalidadId",
			required = false) Long nacionalidadId,
			@RequestParam(required = false) @ApiParam(value = "Residence Province ID", name = "provinciaId",
			required = false) Long provinciaId,
			@RequestParam(required = false) @ApiParam(value = "Full Name Like", name = "nombre",
			required = false) String nombre,
			@RequestParam(required = true) @ApiParam(value = "Page Number", name = "page",
			required = true) Integer page,
			@RequestParam(required = true) @ApiParam(value = "Page", name = "size",
			required = true) Integer size
			){
		
		PersonaFisicaFilter filter = new PersonaFisicaFilter();
		if(activo == null) {
			filter.setActivo(true);
		}else {
			filter.setActivo(activo);
		}
		filter.setNacionalidadId(nacionalidadId);
		filter.setNombreCompletoLike(nombre);
		filter.setToken(token);
		filter.setProvinciaId(provinciaId);
		filter.setNumeroIdentificacionLike(numeroIdentificacion);
		filter.setTipoIdentificationId(tipoIdentificationId);
		filter.setPageNumber(page);
		filter.setPageSize(size);
		
		log.info("Request find personasFisicas. Filtro: {}",filter);
		
		return new ResponseEntity<List<PersonaFisicaResponse>>(convert(personaFisicaService.findAll(filter)), HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/personasFisicas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve una persona física por su id",
			notes = "Devuelve una persona física por su id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK",response = PersonaFisicaResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public ResponseEntity<PersonaFisicaResponse> findOne(
			@ApiParam(value = "Id of the person", required = true) @PathVariable long id
			){
		
		return new ResponseEntity<PersonaFisicaResponse>(parse(personaFisicaService.findById(id)),HttpStatus.OK);
	}
	
	
	private List<PersonaFisicaResponse> convert(List<PersonaFisica> list){
		List<PersonaFisicaResponse> response = new ArrayList<PersonaFisicaResponse>();
		if(list != null) {
			for(PersonaFisica persona : list) {
				response.add(parse(persona));
			}
		}
		return response;
	}
	
	
	private PersonaFisicaResponse parse(PersonaFisica persona) {
		PersonaFisicaResponse response = null;
		if(persona !=null) {
			response = new PersonaFisicaResponse();
			response.setApellido1(persona.getApellido1());
			response.setApellido2(persona.getApellido2());
			response.setNombre(persona.getNombre());
			response.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").format(persona.getFechaNacimiento()));
			response.setId(persona.getId());
			response.setTipoIdentificacion(persona.getTipoIdentificacion().getNombre());
			response.setDireccionResidencia(persona.getDireccionResidencia());
			response.setNacionalidad(persona.getNacionalidad().getNombre());
			response.setNumeroIdentificacion(persona.getNumeroIdentificacion());
			response.setTelefono(persona.getTelefono());
			response.setEmail(persona.getEmail());
			response.setProvinciaResidencia(persona.getProvincia().getNombre());
		}
		return response;
	}
}

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
import es.etsit.silcam.bean.response.PersonaJuridicaResponse;
import es.etsit.silcam.entity.PersonaJuridica;
import es.etsit.silcam.filter.PersonaJuridicaFilter;
import es.etsit.silcam.service.PersonaJuridicaService;
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
public class PersonaJuridicaRestController {

	private PersonaJuridicaService personaJuridicaService;
	
	@Autowired
	public void setPersonaJuridicaService(PersonaJuridicaService personaJuridicaService) {
		this.personaJuridicaService = personaJuridicaService;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/personasJuridicas", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de personas juridicas",
			notes = "Devuelve la lista de personas juridicas que corresponden con la query de busqueda")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", responseContainer="list" ,response = PersonaJuridicaResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<PersonaJuridicaResponse>> findAll(
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
			@RequestParam(required = false) @ApiParam(value = "Business Name Like", name = "razonSocial",
			required = false) String razonSocial,
			@RequestParam(required = false) @ApiParam(value = "Representative ID", name = "representanteId",
			required = false) Long representanteId,
			@RequestParam(required = true) @ApiParam(value = "Page Number", name = "page",
			required = true) Integer page,
			@RequestParam(required = true) @ApiParam(value = "Page", name = "size",
			required = true) Integer size
			){
		
		PersonaJuridicaFilter filter = new PersonaJuridicaFilter();
		if(activo == null) {
			filter.setActivo(true);
		}else {
			filter.setActivo(activo);
		}
		filter.setNacionalidadId(nacionalidadId);
		filter.setRazonSocialLike(razonSocial);
		filter.setToken(token);
		filter.setProvinciaId(provinciaId);
		filter.setNumeroIdentificacionLike(numeroIdentificacion);
		filter.setTipoIdentificationId(tipoIdentificationId);
		filter.setRepresentanteId(representanteId);
		filter.setPageNumber(page);
		filter.setPageSize(size);
		
		log.info("Request find personasJuridicas. Filtro: {}",filter);
		
		return new ResponseEntity<List<PersonaJuridicaResponse>>(convert(personaJuridicaService.findAll(filter)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/personasJuridicas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve una persona jurídica por su id",
			notes = "Devuelve una persona jurídica por su id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK",response = PersonaJuridicaResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public ResponseEntity<PersonaJuridicaResponse> findOne(
			@ApiParam(value = "Id of the person", required = true) @PathVariable long id
			){
		
		return new ResponseEntity<PersonaJuridicaResponse>(parse(personaJuridicaService.findById(id)),HttpStatus.OK);
	}
	
	
	
	private List<PersonaJuridicaResponse> convert(List<PersonaJuridica> list){
		List<PersonaJuridicaResponse> response = new ArrayList<PersonaJuridicaResponse>();
		if(list != null) {
			for(PersonaJuridica persona : list) {
				response.add(parse(persona));
			}
		}
		return response;
	}
	
	
	private PersonaJuridicaResponse parse(PersonaJuridica persona) {
		PersonaJuridicaResponse response = null;
		if(persona !=null) {
			response = new PersonaJuridicaResponse();
			response.setRazonSocial(persona.getRazonSocial());
			response.setFechaConstitucion(new SimpleDateFormat("dd/MM/yyyy").format(persona.getFechaConstitucion()));
			response.setId(persona.getId());
			response.setTipoIdentificacion(persona.getTipoIdentificacion().getNombre());
			response.setDireccionFiscal(persona.getDireccionResidencia());
			response.setPaisNacionalidad(persona.getNacionalidad().getNombre());
			response.setNumeroIdentificacion(persona.getNumeroIdentificacion());
			response.setTelefono(persona.getTelefono());
			response.setEmail(persona.getEmail());
			response.setProvinciaFisical(persona.getProvincia().getNombre());
			response.setRepresentante(persona.getRepresentante());
		}
		return response;
	}
}

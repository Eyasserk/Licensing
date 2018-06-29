package es.etsit.silcam.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.etsit.silcam.bean.request.AuthenticationRequest;
import es.etsit.silcam.bean.response.AuthenticationResponse;
import es.etsit.silcam.bean.response.ErrorResponse;
import es.etsit.silcam.bean.response.ExpedienteResponse;
import es.etsit.silcam.entity.Expediente;
import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.entity.PersonaJuridica;
import es.etsit.silcam.exception.AuthenticationException;
import es.etsit.silcam.exception.BadRequestException;
import es.etsit.silcam.filter.ExpedienteFilter;
import es.etsit.silcam.filter.PersonaFisicaFilter;
import es.etsit.silcam.filter.PersonaJuridicaFilter;
import es.etsit.silcam.security.AuthenticationService;
import es.etsit.silcam.service.ExpedienteService;
import es.etsit.silcam.service.PersonaFisicaService;
import es.etsit.silcam.service.PersonaJuridicaService;
import es.etsit.silcam.util.HashUtils;
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
public class AuthenticationRestController {
	
	private ExpedienteService expedienteService;
	private PersonaFisicaService personaFisicaService;
	private PersonaJuridicaService personaJuridicaService;
	private AuthenticationService authenticationService;
	
	@Autowired
	public void setExpedienteService(ExpedienteService expedienteService) {
		this.expedienteService = expedienteService;
	}
	
	@Autowired
	public void setPersonaFisicaService(PersonaFisicaService personaFisicaService) {
		this.personaFisicaService = personaFisicaService;
	}
	
	@Autowired
	public void setPersonaJuridicaService(PersonaJuridicaService personaJuridicaService) {
		this.personaJuridicaService = personaJuridicaService;
	}
	
	@Autowired
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Crea un expediente",
			notes = "Crea un expediente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ExpedienteResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public ResponseEntity<AuthenticationResponse> authenticate(
			@ApiParam(value = "Authentication Request", required = true) @Valid @RequestBody AuthenticationRequest request
			){
		
		log.info("Authentication request: {}",request);
		
		AuthenticationResponse response = new AuthenticationResponse();
		
		if(request.getId() == 1) {
			ExpedienteFilter filter = new ExpedienteFilter();
			filter.setNumeroExpediente(request.getNumber());
			List<Expediente> expedientes = expedienteService.findAll(filter);
			if(expedientes == null || expedientes.isEmpty()){
				throw new AuthenticationException("Expediente no encontrado. Por favor, compruebe el número introducido");
			}
			Expediente expediente = expedientes.get(0);
			long idPersonType = expediente.getTipoSolicitante().getId();
			if(idPersonType == 1) {
				PersonaFisicaFilter personaFilter = new PersonaFisicaFilter();
				personaFilter.setId(expediente.getIdSolicitante());
				personaFilter.setActivo(true);
				List<PersonaFisica> personas = personaFisicaService.findAll(personaFilter);
				if(personas == null || personas.isEmpty()) {
					throw new AuthenticationException("El solicitante se encuentra bloqueado en el sistema. Por favor contacte con su administración");
				}
				PersonaFisica persona = personas.get(0);
				if(!HashUtils.getHash(request.getClave()).equals(persona.getToken())) {
					throw new AuthenticationException("Password incorrecto. Por favor, compruebe la contraseña introducida");
				}
				response.setToken(authenticationService.generateJWT(persona));
				response.setUserId(expediente.getIdSolicitante());
				response.setUserTypeId(idPersonType);
			}else {
				PersonaJuridicaFilter personaFilter = new PersonaJuridicaFilter();
				personaFilter.setId(expediente.getIdSolicitante());
				personaFilter.setActivo(true);
				List<PersonaJuridica> personas = personaJuridicaService.findAll(personaFilter);
				if(personas == null || personas.isEmpty()) {
					throw new RuntimeException("El solicitante se encuentra bloqueado en el sistema. Por favor contacte con su administración");
				}
				PersonaJuridica persona = personas.get(0);
				if(!HashUtils.getHash(request.getClave()).equals(persona.getToken())) {
					throw new AuthenticationException("Password incorrecto. Por favor, compruebe la contraseña introducida");
				}
				response.setToken(authenticationService.generateJWT(persona));
				response.setUserId(expediente.getIdSolicitante());
				response.setUserTypeId(idPersonType);
			}
		}else {
			if(request.getPersonType() == 1) {
				PersonaFisicaFilter personaFilter = new PersonaFisicaFilter();
				personaFilter.setActivo(true);
				personaFilter.setNumeroIdentificacion(request.getNumber());
				List<PersonaFisica> personas = personaFisicaService.findAll(personaFilter);
				if(personas == null || personas.isEmpty()) {
					throw new AuthenticationException("Usted no está registrado en el sistema.");
				}
				PersonaFisica persona = personas.get(0);
				if(!HashUtils.getHash(request.getClave()).equals(persona.getToken())) {
					throw new AuthenticationException("Password incorrecto. Por favor, compruebe la contraseña introducida");
				}
				response.setToken(authenticationService.generateJWT(persona));
				response.setUserId(persona.getId());
				response.setUserTypeId(request.getPersonType());
			}else if(request.getPersonType() == 2) {
				PersonaJuridicaFilter personaFilter = new PersonaJuridicaFilter();
				personaFilter.setActivo(true);
				personaFilter.setNumeroIdentificacion(request.getNumber());
				List<PersonaJuridica> personas = personaJuridicaService.findAll(personaFilter);
				if(personas == null || personas.isEmpty()) {
					throw new RuntimeException("Usted no está registrado en el sistema.");
				}
				PersonaJuridica persona = personas.get(0);
				if(!HashUtils.getHash(request.getClave()).equals(persona.getToken())) {
					throw new AuthenticationException("Password incorrecto. Por favor, compruebe la contraseña introducida");
				}
				response.setToken(authenticationService.generateJWT(persona));
				response.setUserId(persona.getId());
				response.setUserTypeId(request.getPersonType());
			}else {
				throw new BadRequestException("Por favor, especifíque el tipo de solicitante");
			}
		}
		return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);
	}
}

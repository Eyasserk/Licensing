package es.etsit.silcam.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.etsit.silcam.bean.request.NotificacionRequest;
import es.etsit.silcam.bean.response.ErrorResponse;
import es.etsit.silcam.bean.response.NotificacionResponse;
import es.etsit.silcam.entity.Notificacion;
import es.etsit.silcam.service.NotificacionService;
import es.etsit.silcam.util.NotificationType;
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
public class NotificacionRestController {

	
	private NotificacionService notificacionService;
	
	@Autowired
	public void setNotificacionService(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}
	
	
	/**
	 * Get all notifications
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/notificaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve una lista paginada de notificaciones",
			notes = "Devuelve una lista paginada de notificaciobes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", responseContainer="list", response = NotificacionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<NotificacionResponse>> findAll(
			@RequestParam(required = true) @ApiParam(value = "Page Number", name = "page",
			required = true) Integer page,
			@RequestParam(required = true) @ApiParam(value = "Page Size", name = "size",
			required = true) Integer size
			){
		log.info("Request find notificaciones.");
		
		return new ResponseEntity<List<NotificacionResponse>> (parse(notificacionService.findAll(page, size)),HttpStatus.OK);
	}
	
	
	/**
	 * Get notificaciones by ID persona
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/notificaciones/personas/{idPersona}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de notificaciones para la persona especificada",
			notes = "Devuelve la lista de notificaciones para la persona especificada")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", responseContainer="list", response = NotificacionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<NotificacionResponse>> findAllByIdAndTipoPersona(
			@ApiParam(value = "Person ID.", required = true) @PathVariable long idPersona
			){
		log.info("Request find notificaciones.");
		
		return new ResponseEntity<List<NotificacionResponse>> (parse(notificacionService.findAllByIdPersona(idPersona)),HttpStatus.OK);
	}
	
	
	/**
	 * Get notifications by ID expediente
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/notificaciones/idExpediente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de notificaciones por id expediente",
			notes = "Devuelve la lista de notificaciones por id expediente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", responseContainer="list", response = NotificacionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<NotificacionResponse>> findAllByIdExpediente(
			@ApiParam(value = "Case ID.", required = true) @PathVariable long id
			){
		log.info("Request find notificaciones por id expediente {}.",id);
		
		return new ResponseEntity<List<NotificacionResponse>> (parse(notificacionService.findByIdExpediente(id)),HttpStatus.OK);
	}
	
	
	/**
	 * Get notifications by numero expediente
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/notificaciones/numExpediente/{numExpediente}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de notificaciones por num expediente",
			notes = "Devuelve la lista de notificaciones por num expediente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", responseContainer="list", response = NotificacionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<NotificacionResponse>> findAllByNumeroExpediente(
			@ApiParam(value = "Case Number.", required = true) @PathVariable String numExpediente
			){
		log.info("Request find notificaciones por número expediente {}.",numExpediente);
		
		return new ResponseEntity<List<NotificacionResponse>> (parse(notificacionService.findByNumeroExpediente(numExpediente)),HttpStatus.OK);
	}
	
	
	/**
	 * Get detail of notification by ID 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/notificaciones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de notificaciones por num expediente",
			notes = "Devuelve la lista de notificaciones por num expediente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = NotificacionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<NotificacionResponse> findAllById(
			@ApiParam(value = "Notification ID.", required = true) @PathVariable String id
			){
		log.info("Request find notificación by id {}.",id);
		
		return new ResponseEntity<NotificacionResponse> (parse(notificacionService.findById(id)),HttpStatus.OK);
	}
	
	/**
	 * Creates a notification
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/notificaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Crea una notificacion nueva",
			notes = "Crea una notificacion nueva")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created", response = NotificacionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<NotificacionResponse> create(
			@Valid @RequestBody NotificacionRequest body
			){
		log.info("Request create notificacion {}.",body);
		
		return new ResponseEntity<NotificacionResponse> (parse(notificacionService.create(parse(body))),HttpStatus.CREATED);
	}
	
	
	/**
	 * Updates the state of a notification
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/notificaciones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Crea una notificacion nueva",
			notes = "Crea una notificacion nueva")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No Content", response = NotificacionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<NotificacionResponse> update(
			@ApiParam(value = "Notification ID.", required = true) @PathVariable String id,
			@RequestBody NotificacionRequest body
			){
		log.info("Request create notificacion {}.",body);
		Notificacion notificacion = parse(body);
		notificacion.setId(id);
		return new ResponseEntity<NotificacionResponse> (parse(notificacionService.update(notificacion)),HttpStatus.NO_CONTENT);
	}
	
	
	private Notificacion parse(NotificacionRequest request) {
		Notificacion notificacion = null;
		if(request != null) {
			notificacion = new Notificacion();
			notificacion.setDescripcion(request.getDescripcion());
			notificacion.setIdExpediente(request.getIdExpediente());
			notificacion.setTitulo(request.getTitulo());
			notificacion.setType(NotificationType.fromValue(request.getType()));
		}
		return notificacion;
	}
	
	private NotificacionResponse parse(Notificacion notificacion) {
		NotificacionResponse response = null;
		if(notificacion != null) {
			response = new NotificacionResponse();
			response.setDescripcion(notificacion.getDescripcion());
			response.setFecha(notificacion.getFecha());
			response.setId(notificacion.getId());
			response.setIdExpediente(notificacion.getIdExpediente());
			response.setNumeroExpediente(notificacion.getNumeroExpediente());
			response.setState(notificacion.getState());
			response.setTitulo(notificacion.getTitulo());
			response.setType(notificacion.getType());
			response.setIdPersona(notificacion.getIdPersona());
		}
		return response;
	}
	
	private List<NotificacionResponse> parse(List<Notificacion> list){
		List<NotificacionResponse> response = new ArrayList<NotificacionResponse>();
		if(list != null) {
			for(Notificacion notificacion : list) {
				response.add(parse(notificacion));
			}
		}
		return response;
	}
}

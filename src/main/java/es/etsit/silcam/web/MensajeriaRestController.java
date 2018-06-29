package es.etsit.silcam.web;

import java.util.ArrayList;
import java.util.Date;
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

import es.etsit.silcam.bean.request.HiloRequest;
import es.etsit.silcam.bean.request.MensajeRequest;
import es.etsit.silcam.bean.response.ErrorResponse;
import es.etsit.silcam.bean.response.HiloResponse;
import es.etsit.silcam.entity.Hilo;
import es.etsit.silcam.service.MensajeriaService;
import es.etsit.silcam.util.Mensaje;
import es.etsit.silcam.util.SenderType;
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
public class MensajeriaRestController {
	
	private MensajeriaService mensajeriaService;
	
	@Autowired
	public void setMensajeriaService(MensajeriaService mensajeriaService) {
		this.mensajeriaService = mensajeriaService;
	}
	
	
	/**
	 * Get Thread List
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/hilos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve una lista paginada de hilos de mensajes",
			notes = "Devuelve una lista paginada de hilos de mensajes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", responseContainer="list", response = HiloResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<HiloResponse>> findAll(
			@RequestParam(required = true) @ApiParam(value = "Page Number", name = "page",
			required = true) Integer page,
			@RequestParam(required = true) @ApiParam(value = "Page Size", name = "size",
			required = true) Integer size
			){
		log.info("Request find Hilos.");
		
		return new ResponseEntity<List<HiloResponse>> (parse(mensajeriaService.findAll(page, size)),HttpStatus.OK);
	}
	
	
	/**
	 * Get thread by ID
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/hilos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve un hilo de mensajes",
			notes = "Devuelve un hilo de mensajes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = HiloResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<HiloResponse> findById(
			@ApiParam(value = "Id of the thread.", required = true) @PathVariable String id
			){
		log.info("Request find thread {}",id);
		
		return new ResponseEntity<HiloResponse>(parse(mensajeriaService.findById(id)),HttpStatus.OK);
	}
	
	
	
	/**
	 * Get threads by Case ID
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/hilos/expediente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve un hilo de mensajes",
			notes = "Devuelve un hilo de mensajes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", responseContainer="list" ,response = HiloResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<HiloResponse>> findByIdExpediente(
			@ApiParam(value = "Id of the case.", required = true) @PathVariable long id
			){
		log.info("Request find threads by case ID {}",id);
		
		return new ResponseEntity<List<HiloResponse>>(parse(mensajeriaService.findByIdExpediente(id)),HttpStatus.OK);
	}
	
	
	/**
	 * Creates a new Thread 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/hilos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Crea un hilo de mensajes",
			notes = "Crea un hilo de mensajes")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created", response = HiloResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<HiloResponse> createThread(
			@Valid @RequestBody HiloRequest body
			){
		log.info("Request create thread {}",body);
		
		return new ResponseEntity<HiloResponse>(parse(mensajeriaService.create(parse(body))),HttpStatus.CREATED);
	}
	
	
	/**
	 * Creates a new Message
	 */
	/**
	 * Creates a new Thread 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/hilos/{id}/mensajes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Crea un mensaje dentro de un hilo",
			notes = "Crea un mensaje dentro de un hilo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = HiloResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<HiloResponse> createThread(
			@ApiParam(value = "Id of the case.", required = true) @PathVariable String id,
			@Valid @RequestBody MensajeRequest body
			
			){
		log.info("Request create messgae {} to thread {}",body,id);
		
		return new ResponseEntity<HiloResponse>(parse(mensajeriaService.addMensaje(id, parse(body))), HttpStatus.CREATED);
	}
	
	
	/**
	 * Deletes a thread 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/hilos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Elimina un hilo",
			notes = "Elimina un hilo")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No Content", response = HiloResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<String> deleteThread(
			@ApiParam(value = "Id of the case.", required = true) @PathVariable String id
			
			){
		log.info("Request delete thread {}",id);
		
		mensajeriaService.delete(id);
		
		return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	private Mensaje parse(MensajeRequest request) {
		Mensaje mensaje = null;
		if(request != null) {
			mensaje = new Mensaje();
			mensaje.setFecha(new Date());
			mensaje.setEstado(1);
			mensaje.setContenido(request.getContenido());
			mensaje.setSender(SenderType.fromValue(request.getSenderType()));
		}
		return mensaje;
	}
	
	private Hilo parse(HiloRequest request) {
		Hilo hilo = null;
		if(request != null) {
			hilo = new Hilo();
			List<Mensaje> mensajes = new ArrayList<Mensaje>();
			mensajes.add(parse(request.getMensaje()));
			hilo.setMensajes(mensajes);
			hilo.setIdExpediente(request.getIdExpediente());
			hilo.setTitulo(request.getTitulo());
		}
		return hilo;
	}
	
	private HiloResponse parse(Hilo hilo) {
		HiloResponse response = null;
		if(hilo != null) {
			response = new HiloResponse();
			response.setId(hilo.getId());
			response.setIdExpediente(hilo.getIdExpediente());
			response.setTitulo(hilo.getTitulo());
			response.setMensajes(hilo.getMensajes());
		}
		return response;
	}
	
	private List<HiloResponse> parse(List<Hilo> list){
		List<HiloResponse> response = new ArrayList<HiloResponse>();
		if(list != null) {
			for(Hilo hilo : list) {
				response.add(parse(hilo));
			}
		}
		return response;
	}
	
}

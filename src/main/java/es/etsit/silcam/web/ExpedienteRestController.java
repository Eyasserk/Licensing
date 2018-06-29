package es.etsit.silcam.web;

import java.text.SimpleDateFormat;
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

import es.etsit.silcam.bean.request.ExpedienteRequest;
import es.etsit.silcam.bean.request.ParcelaRequest;
import es.etsit.silcam.bean.response.ErrorResponse;
import es.etsit.silcam.bean.response.ExpedienteResponse;
import es.etsit.silcam.entity.Expediente;
import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.entity.PersonaJuridica;
import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.entity.TipoPersona;
import es.etsit.silcam.entity.gis.Parcela;
import es.etsit.silcam.filter.ExpedienteFilter;
import es.etsit.silcam.service.ExpedienteService;
import es.etsit.silcam.service.MineralService;
import es.etsit.silcam.service.PersonaFisicaService;
import es.etsit.silcam.service.PersonaJuridicaService;
import es.etsit.silcam.service.ProvinciaService;
import es.etsit.silcam.service.TipoExpedienteService;
import es.etsit.silcam.service.TipoSolicitudService;
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
public class ExpedienteRestController {
	
	
	private ExpedienteService expedienteService;
	private MineralService mineralService;
	private ProvinciaService provinciaService;
	private PersonaFisicaService personaFisicaService;
	private PersonaJuridicaService personaJuridicaService;
	private TipoSolicitudService tipoSolicitudService;
	private TipoExpedienteService tipoExpedienteService;
	
	@Autowired
	public void setExpedienteService(ExpedienteService expedienteService) {
		this.expedienteService = expedienteService;
	}
	
	@Autowired
	public void setMineralService(MineralService mineralService) {
		this.mineralService = mineralService;
	}
	
	@Autowired
	public void setProvinciaService(ProvinciaService provinciaService) {
		this.provinciaService = provinciaService;
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
	public void setTipoExpedienteService(TipoExpedienteService tipoExpedienteService) {
		this.tipoExpedienteService = tipoExpedienteService;
	}
	
	@Autowired
	public void setTipoSolicitudService(TipoSolicitudService tipoSolicitudService) {
		this.tipoSolicitudService = tipoSolicitudService;
	}
	
	
	/**
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/expedientes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Devuelve la lista de expedientes",
			notes = "Devuelve la lista de expedientes que corresponden con la query de busqueda")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", responseContainer="list", response = ExpedienteResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<ExpedienteResponse>> find(
			@RequestParam(required = false) @ApiParam(value = "Case Number Like %", name = "numeroExpedienteLike",
			required = false) String numeroExpedienteLike,
			@RequestParam(required = false) @ApiParam(value = "Mineral Group ID", name = "grupoId",
			required = false) Long grupoId,
			@RequestParam(required = false) @ApiParam(value = "Mineral ID", name = "mineralId",
			required = false) Long mineralId,
			@RequestParam(required = false) @ApiParam(value = "Provincia ID", name = "provinciaId",
			required = false) Long provinciaId,
			@RequestParam(required = false) @ApiParam(value = "Case Type ID", name = "tipoExpedienteId",
			required = false) Long tipoExpedienteId,
			@RequestParam(required = false) @ApiParam(value = "Case Phase ID", name = "faseExpedienteId",
			required = false) Long faseExpedienteId,
			@RequestParam(required = false) @ApiParam(value = "Request State ID", name = "estadoSolicitudId",
			required = false) Long estadoSolicitudId,
			@RequestParam(required = false) @ApiParam(value = "Request Type ID", name = "tipoSolicitudId",
			required = false) Long tipoSolicitudId,
			@RequestParam(required = false) @ApiParam(value = "Person Type ID", name = "tipoPersonaId",
			required = false) Long tipoPersonaId,
			@RequestParam(required = false) @ApiParam(value = "Person ID", name = "personId",
			required = false) Long personId,
			@RequestParam(required = false) @ApiParam(value = "Register Start Date", name = "fechaInicio",
			required = false) Date fechaInicio,
			@RequestParam(required = false) @ApiParam(value = "Register End Date", name = "fechaFin",
			required = false) Date fechaFin,
			@RequestParam(required = false) @ApiParam(value = "Activity Start Date from", name = "fechaInicioActividadStart",
			required = false) Date fechaInicioActividadStart,
			@RequestParam(required = false) @ApiParam(value = "Activity Start Date to", name = "fechaInicioActividadEnd",
			required = false) Date fechaInicioActividadEnd,
			@RequestParam(required = false) @ApiParam(value = "Activity End Date from", name = "fechaFinActividadStart",
			required = false) Date fechaFinActividadStart,
			@RequestParam(required = false) @ApiParam(value = "Activity End Date to", name = "fechaFinActividadEnd",
			required = false) Date fechaFinActividadEnd,
			@RequestParam(required = true) @ApiParam(value = "Page Number", name = "page",
			required = true) Integer page,
			@RequestParam(required = true) @ApiParam(value = "Page", name = "size",
			required = true) Integer size
			) {
		
		ExpedienteFilter filter = new ExpedienteFilter();
		filter.setFechaFinActividadEnd(fechaFinActividadEnd);
		filter.setFechaFinActividadStart(fechaFinActividadStart);
		filter.setNumeroExpedienteLike(numeroExpedienteLike);
		filter.setFechaInicioActividadEnd(fechaInicioActividadEnd);
		filter.setFechaInicioActividadStart(fechaInicioActividadStart);
		filter.setFechaInicioExpedienteEnd(fechaFin);
		filter.setFechaInicioExpedienteStart(fechaInicio);
		filter.setGrupoMineralId(grupoId);
		filter.setProvinciaId(provinciaId);
		filter.setIdSolicitante(personId);
		filter.setTipoSolicitanteId(tipoPersonaId);
		filter.setMineralId(mineralId);
		filter.setFaseExpedienteId(faseExpedienteId);
		filter.setEstadoSolicitudId(estadoSolicitudId);
		filter.setTipoExpedienteId(tipoExpedienteId);
		filter.setTipoSolicitudId(tipoSolicitudId);
		filter.setPageSize(size);
		filter.setPageNumber(page);
		
		
		log.info("Request find expedientes. Filter: {}",filter);
		
		return new ResponseEntity<List<ExpedienteResponse>>(convert(expedienteService.findAll(filter)), HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/expedientes/{idExpediente}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Obtiene un expediente por su ID",
			notes = "Obtiene un expediente por su ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ExpedienteResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<ExpedienteResponse> getOne(
			@ApiParam(value = "Id of the case.", required = true) @PathVariable long idExpediente
			) {
		log.info("Request find expediente: {}", idExpediente);
		
		return new ResponseEntity<ExpedienteResponse>(convert(expedienteService.findById(idExpediente)), HttpStatus.OK);	
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/expedientes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Crea un expediente",
			notes = "Crea un expediente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ExpedienteResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<ExpedienteResponse> create(
			@Valid @RequestBody ExpedienteRequest body
			) {
		log.info("Request create expediente: {}", body);
		
		return new ResponseEntity<ExpedienteResponse>(convert(expedienteService.create(convert(body))), HttpStatus.CREATED);	
	}
	
	
	private ExpedienteResponse convert(Expediente expediente) {
		ExpedienteResponse response = null;
		if(expediente != null) {
			response = new ExpedienteResponse();
			response.setEstado(expediente.getEstado());
			response.setFase(expediente.getFase());
			response.setFechaInicioExpediente(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(expediente.getFechaInicioExpediente()));
			response.setFechaInicioActividad(new SimpleDateFormat("dd/MM/yyyy").format(expediente.getFechaInicioActividad()));
			response.setFechaFinActividad(new SimpleDateFormat("dd/MM/yyyy").format(expediente.getFechaFinActividad()));
			response.setId(expediente.getId());
			response.setNumeroExpediente(expediente.getNumeroExpediente());
			if(expediente.getTipoSolicitante().getId() == 1) {
				//Persona Fisica
				PersonaFisica persona = personaFisicaService.findById(expediente.getIdSolicitante());
				response.setNombreSolicitante(persona.getNombre()+" "+persona.getApellido1()+" "+(persona.getApellido2() == null ? "": persona.getApellido2()));
				response.setNumeroIdentificacionSolicitante(persona.getNumeroIdentificacion());
			}else {
				//Persona Juridica
				PersonaJuridica persona = personaJuridicaService.findById(expediente.getIdSolicitante());
				response.setNombreSolicitante(persona.getRazonSocial());
				response.setNumeroIdentificacionSolicitante(persona.getNumeroIdentificacion());
			}
			response.setParcela(expediente.getParcela());
			response.setMineral(expediente.getMineral());
			response.setProvincia(expediente.getProvincia());
			response.setTipoExpediente(expediente.getTipoExpediente());
			response.setTipoSolicitud(expediente.getTipoSolicitud());
		}
		log.info("Response: {}",response);
		return response;
	}
	
	private List<ExpedienteResponse> convert(List<Expediente> expedientes){
		List<ExpedienteResponse> list = new ArrayList<ExpedienteResponse>();
		if(expedientes != null) {
			for(Expediente expediente: expedientes) {
				list.add(convert(expediente));
			}
		}
		return list;
	}
	
	private Expediente convert(ExpedienteRequest request) {
		Expediente expediente = new Expediente();
		expediente.setMineral(mineralService.findById(request.getMineral()));
		//Tipo Expediente
		expediente.setTipoExpediente(tipoExpedienteService.findById(request.getIdTipoExpediente()));
		//Tipo Solicitud
		expediente.setTipoSolicitud(tipoSolicitudService.findById(request.getIdTipoSolicitud()));
		
		//Provincia
		Provincia provincia = provinciaService.findById(request.getParcela().getProvinciaId());
		
		//Parcela
		Parcela parcela = convert(request.getParcela());
		if(provincia != null) {
			parcela.setProvincia(provincia.getNombre());
		}
		
		expediente.setParcela(parcela);
		expediente.setIdSolicitante(request.getIdPersonaSolicitante());
		TipoPersona tipoPersona = new TipoPersona();
		tipoPersona.setId(request.getTipoPersonaSolicitante());
		expediente.setTipoSolicitante(tipoPersona);
		expediente.setFechaFinActividad(request.getActivityEndDate());
		expediente.setFechaInicioActividad(request.getActivityStartDate());
		expediente.setProvincia(provincia);
		return expediente;
	}
	
	private Parcela convert(ParcelaRequest request) {
		Parcela parcela = new Parcela();
		parcela.setArea(request.getArea());
		parcela.setCoordenadas(request.getCoordenadas());
		return parcela;
	}

}

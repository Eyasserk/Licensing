package es.etsit.silcam.web;

import java.text.SimpleDateFormat;
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

import es.etsit.silcam.bean.request.ExpedienteRequest;
import es.etsit.silcam.bean.request.ParcelaRequest;
import es.etsit.silcam.bean.response.ErrorResponse;
import es.etsit.silcam.bean.response.ExpedienteResponse;
import es.etsit.silcam.entity.Expediente;
import es.etsit.silcam.entity.Mineral;
import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.entity.PersonaJuridica;
import es.etsit.silcam.entity.TipoPersona;
import es.etsit.silcam.entity.gis.Parcela;
import es.etsit.silcam.service.ExpedienteService;
import es.etsit.silcam.service.MineralService;
import es.etsit.silcam.service.PersonaFisicaService;
import es.etsit.silcam.service.PersonaJuridicaService;
import es.etsit.silcam.service.ProvinciaService;
import es.etsit.silcam.service.TipoExpedienteService;
import es.etsit.silcam.service.TipoSolicitudService;
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
			@ApiResponse(code = 200, message = "OK", response = ExpedienteResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	public @ResponseBody ResponseEntity<List<ExpedienteResponse>> find(
			
			) {
		log.info("Request find expedientes");
		
		return new ResponseEntity<List<ExpedienteResponse>>(convert(expedienteService.findAll(null)), HttpStatus.OK);
		
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
			response.setArea(expediente.getArea());
			response.setProvincias(expediente.getProvincias());
			response.setEstado(expediente.getEstado());
			response.setFase(expediente.getFase());
			response.setFechaInicioActividad(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(expediente.getFechaInicioExpediente()));
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
			response.setParcelas(expediente.getParcelas());
			response.setMinerales(expediente.getMinerales());
			response.setTipoExpediente(expediente.getTipoExpediente());
			response.setTipoSolicitud(expediente.getTipoSolicitud());
		}
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
		//Parcelas
		List<Parcela> parcelas = null;
		if(request.getParcelas() != null) {
			parcelas = new ArrayList<Parcela>();
			for(ParcelaRequest parcela : request.getParcelas()) {
				parcelas.add(convert(parcela));
			}
		}
		//Minerales
		List<Mineral> minerales = null;
		if(request.getMinerales() != null) {
			minerales = new ArrayList<Mineral>();
			for(Long idMineral : request.getMinerales()) {
				minerales.add(mineralService.findById(idMineral));
			}
		}
		//Tipo Expediente
		expediente.setTipoExpediente(tipoExpedienteService.findById(request.getIdTipoExpediente()));
		//Tipo Solicitud
		expediente.setTipoSolicitud(tipoSolicitudService.findById(request.getIdTipoSolicitud()));
		
		expediente.setParcelas(parcelas);
		expediente.setMinerales(minerales);
		expediente.setIdSolicitante(request.getIdPersonaSolicitante());
		TipoPersona tipoPersona = new TipoPersona();
		tipoPersona.setId(request.getTipoPersonaSolicitante());
		expediente.setTipoSolicitante(tipoPersona);
		return expediente;
	}
	
	private Parcela convert(ParcelaRequest request) {
		Parcela parcela = new Parcela();
		parcela.setArea(request.getArea());
		parcela.setCoordenadas(request.getCoordenadas());
		parcela.setProvincia(provinciaService.findById(request.getProvinciaId()));
		return parcela;
	}

}

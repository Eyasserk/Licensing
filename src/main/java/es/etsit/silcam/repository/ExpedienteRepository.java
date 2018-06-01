package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.Expediente;

@Repository("expedienteRepository")
public interface ExpedienteRepository extends JpaRepository<Expediente, Long>, JpaSpecificationExecutor<Expediente>{
	
	public void deleteById(long id);
	
	public Expediente findByNumeroExpedienteContaining(String numeroExpediente);
	
	/**
	public List<Expediente> findByFaseexpediente_Id(long faseExpedienteId);
	
	public List<Expediente> findByEstadosolicitud_Id(long estadoSolicitudId);
	
	public List<Expediente> findByIdSolicitante(long idSolicitante);
	
	public List<Expediente> findByMinerales_Id(long mineralId);
	
	@Query("select e from expediente e where e.id in (select e_m.expediente_id from expediente_minerales e_m where e_m.minerales_id = ?1)")
	public List<Expediente> buscarPorGrupoMineral(long grupoMineralId);
	
	@Query("select e from expediente e where e.fase_id = ?2 and e.id in (select e_m.expediente_id from expediente_minerales e_m where e_m.minerales_id = ?1)")
	public List<Expediente> buscarOirGrupoMineralYFase(long grupoMineralId, long faseExpedienteId);
	
	@Query("select e from expediente e where e.estado_id = ?2 and e.id in (select e_m.expediente_id from expediente_minerales e_m where e_m.minerales_id = ?1)")
	public List<Expediente> buscarPorGrupoMineralYEstado(long grupoMineralId, long estadoSolicitudId);
	*/
	
	
}


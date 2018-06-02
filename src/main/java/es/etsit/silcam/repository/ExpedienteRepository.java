package es.etsit.silcam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.Expediente;

@Repository("expedienteRepository")
public interface ExpedienteRepository extends JpaRepository<Expediente, Long>, JpaSpecificationExecutor<Expediente>{
	
	public void deleteById(long id);
	
	public Expediente findByNumeroExpedienteContaining(String numeroExpediente);
	
	@Query(nativeQuery=true, value="select * from concesion.expediente where fase_id = ?1")
	public List<Expediente> findByFaseExpedienteId(long faseExpedienteId);
	
	@Query(nativeQuery=true, value="select * from concesion.expediente where estado_id = ?1")
	public List<Expediente> findByEstadosolicitudId(long estadoSolicitudId);
	
	public List<Expediente> findByIdSolicitante(long idSolicitante);
	
	@Query(nativeQuery=true, value="select * from concesion.expediente where id in (select expediente_id from concesion.expediente_minerales where minerales_id = ?1)")
	public List<Expediente> findByMineralId(long mineralId);
	
	@Query(nativeQuery=true, value="select * from concesion.expediente where id in (select distinct expediente_id from concesion.expediente_minerales where minerales_id in (select id from concesion.mineral where grupo_id = ?1))")
	public List<Expediente> buscarPorGrupoMineral(long grupoMineralId);
	
	@Query(nativeQuery=true, value="select * from concesion.expediente where fase_id = ?2 and id in (select expediente_id from concesion.expediente_minerales where minerales_id = ?1)")
	public List<Expediente> buscarOirGrupoMineralYFase(long grupoMineralId, long faseExpedienteId);
	
	@Query(nativeQuery=true, value="select * from concesion.expediente where estado_id = ?2 and id in (select expediente_id from concesion.expediente_minerales where minerales_id = ?1)")
	public List<Expediente> buscarPorGrupoMineralYEstado(long grupoMineralId, long estadoSolicitudId);
	
	
}


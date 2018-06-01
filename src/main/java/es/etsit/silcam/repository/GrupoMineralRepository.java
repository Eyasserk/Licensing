package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.GrupoMineral;

@Repository("grupoMineralRepository")
public interface GrupoMineralRepository extends JpaRepository<GrupoMineral, Long>{

	public void deleteById(long id);
}

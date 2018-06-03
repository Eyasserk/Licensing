package es.etsit.silcam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.Mineral;

@Repository("mineralRepository")
public interface MineralRepository extends JpaRepository<Mineral, Long>{

	@Query(nativeQuery=true, value="select * from recursos.mineral where grupo_id = ?1")
	public List<Mineral> findByGrupoMineral(long idGrupoMineral);
	
	public void deleteById(long id);

}

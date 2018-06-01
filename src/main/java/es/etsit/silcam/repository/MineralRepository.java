package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.Mineral;

@Repository("mineralRepository")
public interface MineralRepository extends JpaRepository<Mineral, Long>{

	public void deleteById(long id);

}

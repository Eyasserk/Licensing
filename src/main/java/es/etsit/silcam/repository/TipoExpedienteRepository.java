package es.etsit.silcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.etsit.silcam.entity.TipoExpediente;

@Repository("tipoExpedienteRepository")
public interface TipoExpedienteRepository extends JpaRepository<TipoExpediente, Long>{

}

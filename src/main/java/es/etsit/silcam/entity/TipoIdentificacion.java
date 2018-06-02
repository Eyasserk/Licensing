package es.etsit.silcam.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import es.etsit.silcam.core.AbstractMasterEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="TipoIdentificacion", schema="poblacion")
@Getter
@Setter
@ToString
public class TipoIdentificacion extends AbstractMasterEntity{
	
}

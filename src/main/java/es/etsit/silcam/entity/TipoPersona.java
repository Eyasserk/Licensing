package es.etsit.silcam.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import es.etsit.silcam.core.AbstractMasterEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="TipoPersona", schema="poblacion")
@Getter
@Setter
@ToString
public class TipoPersona extends AbstractMasterEntity{
	
}

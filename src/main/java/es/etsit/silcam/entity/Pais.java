package es.etsit.silcam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import es.etsit.silcam.core.AbstractMasterEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Pais", schema="poblacion")
@Getter
@Setter
@ToString
public class Pais extends AbstractMasterEntity {
	
	@Column(name="iso",length=3, nullable=false)
	private int iso;
}

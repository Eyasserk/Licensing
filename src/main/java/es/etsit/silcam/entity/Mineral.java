package es.etsit.silcam.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.etsit.silcam.core.AbstractMasterEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Mineral", schema="recursos")
@Getter
@Setter
@ToString
public class Mineral extends AbstractMasterEntity{
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="grupo_id")
	private GrupoMineral grupo;
	
}

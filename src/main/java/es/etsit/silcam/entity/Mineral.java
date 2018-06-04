package es.etsit.silcam.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.etsit.silcam.core.AbstractMasterEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Mineral", schema="recursos")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mineral extends AbstractMasterEntity{
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=GrupoMineral.class)
	private GrupoMineral grupo;
	
}

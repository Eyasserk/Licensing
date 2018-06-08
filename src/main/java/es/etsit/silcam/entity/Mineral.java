package es.etsit.silcam.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
public class Mineral extends AbstractMasterEntity implements Serializable{
	
	private static final long serialVersionUID = 966133805953866229L;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="grupo_id")
	private GrupoMineral grupo;
	
}

package es.etsit.silcam.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.etsit.silcam.core.AbstractMasterEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="GrupoMineral", schema="recursos")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GrupoMineral extends AbstractMasterEntity implements Serializable{

	private static final long serialVersionUID = 7193363811568610594L;
	
	
}

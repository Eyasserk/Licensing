package es.etsit.silcam.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.etsit.silcam.core.AbstractMasterEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Pais", schema="poblacion")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pais extends AbstractMasterEntity implements Serializable{
	
	private static final long serialVersionUID = 4625459203440389144L;
	
	@Column(name="iso",length=3, nullable=false)
	private int iso;
}

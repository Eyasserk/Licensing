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
@Table(name="Sexo", schema="poblacion")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sexo extends AbstractMasterEntity implements Serializable{
	
	private static final long serialVersionUID = 5528861116756934831L;

}

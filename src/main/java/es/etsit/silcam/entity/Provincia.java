package es.etsit.silcam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Provincia", schema="poblacion")
@Getter
@Setter
@ToString
public class Provincia {
	
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private long id;
	
	@Column(name="nombre", length=40, nullable=false, unique=true)
	private String nombre;
	
	@Override
	public boolean equals(Object o) {
		if(o == null || ! (o instanceof Provincia)) {
			return false;
		}
		Provincia p = (Provincia) o;
		return nombre.equals(p.getNombre());
	}
}

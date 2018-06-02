package es.etsit.silcam.core;

import java.util.UUID;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AbstractEntityMongo {

	private String id;
	
	public void generateId() {
		this.id = UUID.randomUUID().toString();
	}
}

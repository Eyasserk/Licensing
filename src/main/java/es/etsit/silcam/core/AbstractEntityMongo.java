package es.etsit.silcam.core;

import java.util.UUID;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@MappedSuperclass
@ToString
public class AbstractEntityMongo {

	private String id;
	
	public void generateId() {
		this.id = UUID.randomUUID().toString();
	}
}

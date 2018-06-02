package es.etsit.silcam.core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AbstractEntity {

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO)
	private long id;
}

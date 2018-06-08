package es.etsit.silcam.core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@MappedSuperclass
@ToString
public class AbstractEntity {

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO)
	private long id;
}

package es.etsit.silcam.filter;

import org.springframework.data.jpa.domain.Specification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractFilter<E> {

	Integer pageSize;
	Integer pageNumber;
	
	public abstract Specification<E> getSpecifications();
	
	public abstract void reset();
	
}

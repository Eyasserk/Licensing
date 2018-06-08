package es.etsit.silcam.core;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class AbstractFilter<E> implements Filter<E>{
	
	protected Sort sort;
	
	public Sort getSort() {
		if(sort == null) {
			//Por defecto devuelve un ordenado por id ascendente
			return Sort.by(new Order(Direction.ASC,"id"));
		}else {
			return sort;
		}
	}
	
}

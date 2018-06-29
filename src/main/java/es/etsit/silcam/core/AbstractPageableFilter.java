package es.etsit.silcam.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class AbstractPageableFilter<E> extends AbstractFilter<E>{

	@Value("${silcam.domain.filter.page.size:10}")
	private Integer pageSize = 10;
	
	private Integer pageNumber = 0;
	
	public PageRequest getPageRequest(){
		return PageRequest.of(pageNumber, pageSize, getSort()); 
	}
	
	@Override
	public boolean isPageable() {
		return true;
	}
	
}

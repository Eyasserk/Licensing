package es.etsit.silcam.entity.gis;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import es.etsit.silcam.core.AbstractEntityMongo;
import es.etsit.silcam.util.Coordenada;
import lombok.Getter;
import lombok.Setter;

@Document(collection="geo.parcela")
@Getter
@Setter
public class Parcela extends AbstractEntityMongo  implements Serializable{
	
	private static final long serialVersionUID = -1343584731994578015L;

	private long expedienteId;
	
	private List<Coordenada> coordenadas;
	
	private String provincia;
	
	private double area;
}

package es.etsit.silcam.entity.gis;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import es.etsit.silcam.core.AbstractEntityMongo;
import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.util.Coordenada;
import lombok.Getter;
import lombok.Setter;

@Document(collection="geo.parcela")
@Getter
@Setter
public class Parcela extends AbstractEntityMongo{
	
	private long expedienteId;
	
	private List<Coordenada> coordenadas;
	
	private Provincia provincia;
	
	private double area;
}

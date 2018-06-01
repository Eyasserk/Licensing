package es.etsit.silcam.entity.gis;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import es.etsit.silcam.entity.Provincia;
import es.etsit.silcam.util.Coordenada;
import lombok.Data;

@Document(collection="geo.parcela")
@Data
public class Parcela {

    private String id;
	
	private long expedienteId;
	
	private List<Coordenada> coordenadas;
	
	private Provincia provincia;
	
	private double area;
}

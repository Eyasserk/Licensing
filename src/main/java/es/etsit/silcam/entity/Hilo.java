package es.etsit.silcam.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import es.etsit.silcam.core.AbstractEntityMongo;
import es.etsit.silcam.util.Mensaje;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="mensajeria.hilo")
public class Hilo extends AbstractEntityMongo{

	private long idExpediente;
	private String titulo;
	private List<Mensaje> mensajes;
}

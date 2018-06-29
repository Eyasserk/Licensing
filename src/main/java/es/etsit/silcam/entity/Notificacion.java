package es.etsit.silcam.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import es.etsit.silcam.core.AbstractEntityMongo;
import es.etsit.silcam.util.NotificationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection="mensajeria.notificacion")
public class Notificacion extends AbstractEntityMongo{

	private int state;
	private NotificationType type;
	private long idPersona;
	private long idExpediente;
	private String numeroExpediente;
	private String titulo;
	private String descripcion;
	private Date fecha;
}

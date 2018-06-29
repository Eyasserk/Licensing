package es.etsit.silcam.util;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mensaje {
	
	private Date fecha;
	private String contenido;
	private int estado;
	private SenderType sender;
	
}

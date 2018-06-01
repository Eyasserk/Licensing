package es.etsit.silcam.exception;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = -155958673106676495L;

	public BadRequestException() {
		super();
	}
	
	public BadRequestException(String msg) {
		super(msg);
	}
	
	public BadRequestException(Throwable cause) {
		super(cause);
	}
	
	public BadRequestException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}

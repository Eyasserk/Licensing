package es.etsit.silcam.exception;

public class AuthenticationException extends RuntimeException{

	private static final long serialVersionUID = -3095662756559844196L;

	public AuthenticationException() {
		super();
	}
	
	public AuthenticationException(String msg) {
		super(msg);
	}
	
	public AuthenticationException(Throwable cause) {
		super(cause);
	}
	
	public AuthenticationException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}

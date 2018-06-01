package es.etsit.silcam.exception;

public class ConflictException extends RuntimeException{

	private static final long serialVersionUID = 6982097469373456507L;

	public ConflictException() {
		super();
	}
	
	public ConflictException(String msg) {
		super(msg);
	}
	
	public ConflictException(Throwable cause) {
		super(cause);
	}
	
	public ConflictException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}


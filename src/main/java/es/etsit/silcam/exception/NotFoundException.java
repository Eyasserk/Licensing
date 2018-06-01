package es.etsit.silcam.exception;

public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6757737013228725083L;

	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String msg) {
		super(msg);
	}
	
	public NotFoundException(Throwable cause) {
		super(cause);
	}
	
	public NotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}


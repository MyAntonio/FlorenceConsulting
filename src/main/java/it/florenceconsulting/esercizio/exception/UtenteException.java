package it.florenceconsulting.esercizio.exception;

public class UtenteException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private final String message;
	private final Integer code;
	
	public UtenteException(String message) {
		this.message = message;
		this.code = 500;
	}
	
	public UtenteException(String message, Integer code) {
		this.message = message;
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}
}

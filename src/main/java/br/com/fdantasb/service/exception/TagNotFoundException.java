package br.com.fdantasb.service.exception;

public class TagNotFoundException extends IllegalArgumentException{

	private static final long serialVersionUID = 1239218084169404768L;

	public TagNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public TagNotFoundException(String message) {
    	super(message);
    }
}

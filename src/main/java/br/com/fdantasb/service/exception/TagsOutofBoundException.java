package br.com.fdantasb.service.exception;

public class TagsOutofBoundException extends ArrayIndexOutOfBoundsException {
	
	private static final long serialVersionUID = -7391941361140541966L;

	public TagsOutofBoundException(String message, Throwable cause){
        super();
    }

    public TagsOutofBoundException(String tagNaoEncontrada) {
    }
}

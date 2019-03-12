package br.com.fdantasb.service.exception;

public class TagNotFoundException extends IllegalArgumentException{

    public TagNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public TagNotFoundException(String tagNaoEncontrada) {
    }
}

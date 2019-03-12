package br.com.fdantasb.service.exception;

public class ProductException extends IllegalArgumentException {



    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductException(String message) {
        super(message);
    }
}

package br.com.fdantasb.service.exception;

public class ProductNotFoundException extends IllegalArgumentException {



    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}

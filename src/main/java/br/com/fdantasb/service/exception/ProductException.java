package br.com.fdantasb.service.Package;

public class ExistentProductException extends IllegalArgumentException {



    public ExistentProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistentProductException(String message) {
        super(message);
    }
}

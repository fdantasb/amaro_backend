package br.com.fdantasb.service.exception;

public class ProductNotFoundException extends IllegalArgumentException {

	private static final long serialVersionUID = 2693881392048944662L;

	public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}

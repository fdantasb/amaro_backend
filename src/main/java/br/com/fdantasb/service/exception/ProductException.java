package br.com.fdantasb.service.exception;

public class ProductException extends IllegalArgumentException {

	private static final long serialVersionUID = 2693881392048944662L;

	public ProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductException(String message) {
        super(message);
    }
}

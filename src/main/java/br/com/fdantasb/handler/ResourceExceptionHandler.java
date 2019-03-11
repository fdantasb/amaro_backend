package br.com.fdantasb.handler;

import br.com.fdantasb.model.ErrorDetails;
import br.com.fdantasb.service.exception.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorDetails> ProductException(MethodArgumentNotValidException ex, HttpServletRequest request){
        ErrorDetails result = new ErrorDetails();
        result.setTitle(ex.getMessage());
        result.setStatus(404l);
        result.setTimestamp(System.currentTimeMillis());

        ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}

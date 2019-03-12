package br.com.fdantasb.handler;

import br.com.fdantasb.model.ErrorDetails;
import br.com.fdantasb.service.exception.ProductNotFoundException;
import br.com.fdantasb.service.exception.TagNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDetails> productNotFoundException(ProductNotFoundException ex, HttpServletRequest request){
        ErrorDetails result = new ErrorDetails();
        result.setTitle(ex.getMessage());
        result.setStatus(404l);
        result.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    public ResponseEntity<ErrorDetails> tagNotFoundException(TagNotFoundException ex, HttpServletRequest request){
        ErrorDetails result = new ErrorDetails();
        result.setTitle(ex.getMessage());
        result.setStatus(404l);
        result.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}

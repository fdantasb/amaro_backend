package br.com.fdantasb.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fdantasb.model.ErrorDetails;
import br.com.fdantasb.service.exception.ProductNotFoundException;
import br.com.fdantasb.service.exception.TagNotFoundException;
import br.com.fdantasb.service.exception.TagsOutofBoundException;

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

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<ErrorDetails> tagNotFoundException(TagNotFoundException ex, HttpServletRequest request){
        ErrorDetails result = new ErrorDetails();
        result.setTitle(ex.getMessage());
        result.setStatus(404l);
        result.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    
    @ExceptionHandler(TagsOutofBoundException.class)
    public ResponseEntity<ErrorDetails> tagsOutofBoundException(TagsOutofBoundException ex, HttpServletRequest request){
        ErrorDetails result = new ErrorDetails();
        result.setTitle(ex.getMessage());
        result.setStatus(400l);
        result.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}

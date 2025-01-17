package com.revature.pokedex.util.aspects;


import com.revature.pokedex.util.exceptions.AuthenticationException;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import jdk.jfr.Experimental;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

// Becuase this aspect was so common we leverage something knows as a @RestControllerAdvice
@RestControllerAdvice
public class ErrorExceptionAspect {
    @ExceptionHandler({InvalidRequestException.class, IOException.class})
    public ResponseEntity<String> handleException(Exception e){
        String message = "Caught the invalid request with : " + e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // only use with void, if you plan to return some JSON body you have to use ResponseEntity<>
    public void handleException2(Exception e){
        String message = "Caught the invalid request2 with : " + e.getMessage();
        System.out.println(message);
    }

    @ExceptionHandler(ResourcePersistanceException.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // this requires a void, it cannot handle any other return type
    public ResponseEntity<String> resourceException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> exception(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

package peaksoft.restcrudlms.exceptions.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.restcrudlms.exceptions.ExceptionResponse;
import peaksoft.restcrudlms.exceptions.ObjectNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //404
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerObjectNotFoundException(ObjectNotFoundException e) {
        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),
                e.getMessage()
                );
    }
}

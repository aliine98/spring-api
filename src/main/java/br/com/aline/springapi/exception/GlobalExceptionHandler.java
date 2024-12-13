package br.com.aline.springapi.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseError> handleBusinessException(BusinessException e) {
        ResponseError res = new ResponseError();
        res.setError(e.getMessage());
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseError> handleEntityNotFoundException(EntityNotFoundException e) {
        ResponseError res = new ResponseError();
        res.setError(e.getMessage());
        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public  ResponseEntity<ResponseError> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ResponseError res = new ResponseError();
        res.setError("Já existe um usuário cadastrado com este login! Tente algo diferente.");
        res.setStatusCode(HttpStatus.CONFLICT.value());
        res.setStatus(HttpStatus.CONFLICT.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
            ResponseError res = new ResponseError();
            StringBuilder stringBuilder = new StringBuilder();
            e.getAllErrors().forEach(error -> stringBuilder.append(error.getDefaultMessage()).append(" ").append("\n "));
            res.setError(stringBuilder.toString());
            res.setStatusCode(status.value());
            res.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}

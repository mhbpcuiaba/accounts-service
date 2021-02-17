package com.awsome.techfin.accounts.service.application.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AccountsServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    protected ResponseEntity<Object> handler(Exception ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        return buildResponseEntity(
                new AccountsServiceResponseError.Builder()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(ex)
                        .messageDetail("Erro não esperado.")
                        .date(new Date())
                        .build()
        );
    }

    @ExceptionHandler(value = {AccountNotFoundException.class})
    protected ResponseEntity<Object> handlerAccountNotFoundException(Exception ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        return buildResponseEntity(
                new AccountsServiceResponseError.Builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(ex)
                        .messageDetail(ex.getMessage())
                        .date(new Date())
                        .build()
        );
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handlerDataIntegrityViolationException(Exception ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        org.hibernate.exception.ConstraintViolationException cve = (org.hibernate.exception.ConstraintViolationException) ex.getCause();
        String constraintName = cve.getConstraintName();
        return buildResponseEntity(
                new AccountsServiceResponseError.Builder()
                        .httpStatus(HttpStatus.CONFLICT)
                        .message("breaks " + constraintName)
                        .messageDetail(ex.getMessage())
                        .date(new Date())
                        .build()
        );
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handlerConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        final List<String> errors = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return buildResponseEntity(
                new AccountsServiceResponseError.Builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .message(errors.toString())
                        .messageDetail(ex.getMessage())
                        .date(new Date())
                        .build()
        );
    }

    private ResponseEntity<Object> buildResponseEntity(AccountsServiceResponseError errorMapper) {
        return new ResponseEntity<>(errorMapper, errorMapper.getHttpStatus());
    }
}

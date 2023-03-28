package com.verter.config.advice;

import com.verter.exception.EmailAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(EmailAlreadyRegisteredException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiException handleEmailAlreadyRegisteredException( EmailAlreadyRegisteredException exception ) {
    return new ApiException(List.of(exception.getMessage()));
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ApiException handleBadCredentialsException( BadCredentialsException exception) {
    //String def = "No user with given credentials!";
    return new ApiException(List.of(exception.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ApiException handleMethodArgumentNotValidException( MethodArgumentNotValidException exception ) {
    return new ApiException(exception
      .getBindingResult()
      .getFieldErrors()
      .stream()
      .map(error -> error.getField() + " - " + error.getDefaultMessage())
      .toList());
  }
}

package com.verter.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {

  private final String message;

  public EmailAlreadyRegisteredException() {
    this.message = "Email already registered in the system!";
  }

  public EmailAlreadyRegisteredException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}

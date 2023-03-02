package com.junia.jeeproject.exception.client;

import com.junia.jeeproject.exception.HttpException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {

  public NotFoundException(String message, Throwable cause) {
    super(HttpStatus.NOT_FOUND, message, cause);
  }

  public NotFoundException(String message) {
    this(message, null);
  }

  public NotFoundException(Throwable cause) {
    this(cause.getMessage(), cause);
  }

  public NotFoundException() {
    this("Not found", null);
  }
}

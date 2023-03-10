package heavynimbus.templatespringrest.exception.client;

import heavynimbus.templatespringrest.exception.HttpException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException {

  public BadRequestException(String message, Throwable cause) {
    super(HttpStatus.BAD_REQUEST, message, cause);
  }

  public BadRequestException(String message) {
    this(message, null);
  }

  public BadRequestException(Throwable cause) {
    this(cause.getMessage(), cause);
  }

  public BadRequestException() {
    this("Bad request", null);
  }
}

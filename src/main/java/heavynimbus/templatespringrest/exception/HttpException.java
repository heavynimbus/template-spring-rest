package heavynimbus.templatespringrest.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class HttpException extends RuntimeException {

  private final HttpStatus status;


  private final Map<String, Object> details;

  protected HttpException(HttpStatus status, String message, Throwable cause) {
    super(message, cause);
    this.status = status;
    this.details = new HashMap<>();
  }

  public HttpException withDetail(String key, Object value) {
    this.details.put(key, value);
    return this;
  }
}

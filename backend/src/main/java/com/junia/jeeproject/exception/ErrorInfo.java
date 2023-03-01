package com.junia.jeeproject.exception;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {

  private HttpStatus status;
  private String message;
  private String url;
  private Map<String, Object> details;
}

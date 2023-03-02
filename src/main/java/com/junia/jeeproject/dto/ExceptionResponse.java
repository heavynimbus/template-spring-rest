package com.junia.jeeproject.dto;

import java.time.Instant;
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
public class ExceptionResponse {

  private HttpStatus status;
  private String message;
  private String url;
  private Map<String, Object> details;
  @Builder.Default
  private Long timestamp = Instant.now().toEpochMilli();
}

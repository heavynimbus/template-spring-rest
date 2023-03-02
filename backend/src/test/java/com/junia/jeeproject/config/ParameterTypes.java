package com.junia.jeeproject.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DocStringType;
import io.cucumber.java.ParameterType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class ParameterTypes {

  private final ObjectMapper objectMapper;

  @ParameterType("GET|POST|PUT|PATCH|DELETE")
  public HttpMethod httpMethod(String key) {
    return HttpMethod.valueOf(key);
  }

  @ParameterType(".*")
  public HttpStatus httpStatus(String key) {
    return HttpStatus.valueOf(key);
  }

  @ParameterType("(/[\\w-%\\+.~#?&//=]*)+")
  public String uri(String uri) {
    return uri;
  }

  @ParameterType("true|false")
  public Boolean bool(String bool) {
    return Boolean.valueOf(bool);
  }

  @DocStringType
  public JsonNode jsonNode(String docString) {
    return docString(docString, JsonNode.class);
  }

  public <T> T docString(String docString, Class<T> clazz) {
    try {
      return objectMapper.readValue(docString, clazz);
    } catch (Exception e) {
      throw new RuntimeException("Unable to map parameter as a %s".formatted(clazz), e);
    }
  }
}

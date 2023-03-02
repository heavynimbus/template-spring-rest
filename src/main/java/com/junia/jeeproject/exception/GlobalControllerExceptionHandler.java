package com.junia.jeeproject.exception;

import com.junia.jeeproject.dto.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for all controllers.
 */
@Log4j2
@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(HttpException.class)
  public ResponseEntity<ExceptionResponse> handleHttpException(HttpException e,
      HttpServletRequest request) {
    return ResponseEntity.status(e.getStatus())
        .body(ExceptionResponse.builder()
            .status(e.getStatus())
            .message(e.getMessage())
            .url(request.getRequestURI())
            .details(e.getDetails())
            .build());
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      @NonNull HttpRequestMethodNotSupportedException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An HttpRequestMethodNotSupportedException has been thrown", ex);
    return handleSpringException(status, "Method not allowed for this path", request,
        () -> Map.of("method", ex.getMethod()));
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      @NonNull HttpMediaTypeNotSupportedException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An HttpMediaTypeNotSupportedException has been thrown", ex);
    return handleSpringException(status, "Media type not supported", request,
        () -> Map.of("supportedMediaTypes", ex.getSupportedMediaTypes()));
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
      @NonNull HttpMediaTypeNotAcceptableException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An HttpMediaTypeNotAcceptableException has been thrown", ex);
    return handleSpringException(status, "Media type not acceptable", request,
        () -> Map.of("supportedMediaTypes", ex.getSupportedMediaTypes()));
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      @NonNull MissingPathVariableException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An MissingPathVariableException has been thrown", ex);
    return handleSpringException(status, "Missing path variable", request,
        () -> Map.of("variableName", ex.getVariableName()));
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      @NonNull MissingServletRequestParameterException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An MissingServletRequestParameterException has been thrown", ex);
    return handleSpringException(status, "Missing request parameter", request,
        () -> Map.of("parameterName", ex.getParameterName()));
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      @NonNull MissingServletRequestPartException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An MissingServletRequestPartException has been thrown", ex);
    return handleSpringException(status, "Missing request part", request,
        () -> Map.of("requestPartName", ex.getRequestPartName()));
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
      @NonNull ServletRequestBindingException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An ServletRequestBindingException has been thrown", ex);
    return handleSpringException(status, "Request binding exception", request,
        () -> Map.of("message", ex.getMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      @NonNull MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An MethodArgumentNotValidException has been thrown", ex);
    return handleSpringException(status, "Method argument not valid", request,
        () -> {
          BeanPropertyBindingResult bindingResult = (BeanPropertyBindingResult) ex.getBindingResult();
          List<Map<String, String>> errors = bindingResult.getFieldErrors().stream()
              .map(fieldError -> Map.of(
                  "field", fieldError.getField(),
                  "message",
                  Optional.ofNullable(fieldError.getDefaultMessage()).orElse("unknown error")
              )).toList();
          return Map.of("errors", errors);
        });
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
      @NonNull NoHandlerFoundException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An NoHandlerFoundException has been thrown", ex);
    return handleSpringException(status, "No handler found", request,
        () -> Map.of("httpMethod", ex.getHttpMethod(),
            "requestURL", ex.getRequestURL()));
  }

  @Override
  protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
      @NonNull AsyncRequestTimeoutException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An AsyncRequestTimeoutException has been thrown", ex);
    return handleSpringException(status, "Async request timeout", request,
        () -> Map.of("message", ex.getMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleErrorResponseException(
      @NonNull ErrorResponseException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An ErrorResponseException has been thrown", ex);
    return handleSpringException(status, "Error response exception", request,
        () -> Map.of("message", ex.getMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleConversionNotSupported(
      @NonNull ConversionNotSupportedException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An ConversionNotSupportedException has been thrown", ex);
    return handleSpringException(status, "Conversion not supported", request,
        () -> Map.of("value", Optional.ofNullable(ex.getValue()),
            "requiredType", Optional.ofNullable(ex.getRequiredType())));
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
      @NonNull TypeMismatchException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An TypeMismatchException has been thrown", ex);
    return handleSpringException(status, "Type mismatch", request,
        () -> Map.of("value", Optional.ofNullable(ex.getValue()),
            "requiredType", Optional.ofNullable(ex.getRequiredType())));
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      @NonNull HttpMessageNotReadableException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An HttpMessageNotReadableException has been thrown", ex);
    return handleSpringException(status, "Http message not readable", request,
        () -> Map.of("message", ex.getMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(
      @NonNull HttpMessageNotWritableException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    log.error("An HttpMessageNotWritableException has been thrown", ex);
    return handleSpringException(status, "Http message not writable", request,
        () -> Map.of("message", ex.getMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      @NonNull Exception ex,
      Object body,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode statusCode,
      @NonNull WebRequest request) {
    log.error("An Exception has been thrown", ex);
    return handleSpringException(statusCode, "Internal server error", request,
        () -> null);
  }

  private ResponseEntity<Object> handleSpringException(
      HttpStatusCode statusCode,
      String message,
      WebRequest request,
      Supplier<Map<String, Object>> detailsSupplier) {
    String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
    Object res = ExceptionResponse.builder()
        .status(HttpStatus.valueOf(statusCode.value()))
        .message(message)
        .url(uri)
        .details(detailsSupplier.get())
        .build();
    return ResponseEntity.status(statusCode).body(res);
  }
}

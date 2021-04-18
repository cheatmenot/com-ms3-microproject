package com.contact.management.sdk.response;

import org.springframework.http.HttpStatus;

public class ResponseSchema<T> {
  private HttpStatus status;
  private String error;
  private String message;
  private T result;

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

}

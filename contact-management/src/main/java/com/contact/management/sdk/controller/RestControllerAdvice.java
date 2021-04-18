package com.contact.management.sdk.controller;

import com.contact.management.sdk.response.ResponseSchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class RestControllerAdvice {

  @ApiResponse(responseCode = "500", description = "Internal Server Error",
      content = @Content(schema = @Schema(implementation = Error500ResponseSchema.class)))
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ResponseEntity<ResponseSchema<Object>> handleGeneralException(Exception e){
    ResponseSchema<Object> resp = new ResponseSchema<>();
    resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    resp.setError(e.getMessage());
    return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private static class Error500ResponseSchema extends ResponseSchema<Object> {
    @Schema(example = "Internal Server Error")
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
  }

}

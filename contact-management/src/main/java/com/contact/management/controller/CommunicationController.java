package com.contact.management.controller;

import com.contact.management.model.Communication;
import com.contact.management.sdk.model.TransactionMessage;
import com.contact.management.sdk.response.ResponseSchema;
import com.contact.management.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

/**
 * CommunicationController
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@RestController
@RequestMapping("/communication")
public class CommunicationController {

  @Autowired
  public CommunicationService communicationService;

  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Communication>> create(
      @Valid @RequestBody Communication communication,
      BindingResult result) {
    ResponseSchema<Communication> response = communicationService.create(communication);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Communication>> update(
      @Valid @RequestBody Communication communication,
      BindingResult result) {
    ResponseSchema<Communication> response = communicationService.update(communication);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<TransactionMessage>> delete(
      @RequestParam(value = "id", required = false, defaultValue = "0") BigInteger id) {
    ResponseSchema<TransactionMessage> response = communicationService.delete(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping(value = "/deleteSoft", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Communication>> deleteSoft(
      @RequestParam(value = "id", required = false, defaultValue = "0") BigInteger id) {
    ResponseSchema<Communication> response = communicationService.deleteSoft(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(value = "/getId", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Communication>> getId(
      @RequestParam(value = "id", required = false, defaultValue = "0") BigInteger id){
    ResponseSchema<Communication> response = communicationService.getId(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<List<Communication>>> getAll() {
    ResponseSchema<List<Communication>> response = communicationService.getAll();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}

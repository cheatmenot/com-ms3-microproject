package com.contact.management.controller;

import com.contact.management.model.Identification;
import com.contact.management.sdk.model.TransactionMessage;
import com.contact.management.sdk.response.ResponseSchema;
import com.contact.management.service.IdentificationService;
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
 * IdentificationController
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@RestController
@RequestMapping("/identification")
public class IdentificationController {

  @Autowired
  public IdentificationService identificationService;

  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Identification>> create(
      @Valid @RequestBody Identification identification,
      BindingResult result) {
    ResponseSchema<Identification> response = identificationService.create(identification);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Identification>> update(
      @Valid @RequestBody Identification identification,
      BindingResult result) {
    ResponseSchema<Identification> response = identificationService.update(identification);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<TransactionMessage>> delete(
      @RequestParam(value = "id", required = false, defaultValue = "0") BigInteger id) {
    ResponseSchema<TransactionMessage> response = identificationService.delete(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping(value = "/deleteSoft", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Identification>> deleteSoft(
      @RequestParam(value = "id", required = false, defaultValue = "0") BigInteger id) {
    ResponseSchema<Identification> response = identificationService.deleteSoft(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(value = "/getId", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Identification>> getId(
      @RequestParam(value = "id", required = false, defaultValue = "0") BigInteger id){
    ResponseSchema<Identification> response = identificationService.getId(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<List<Identification>>> getAll() {
    ResponseSchema<List<Identification>> response = identificationService.getAll();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}

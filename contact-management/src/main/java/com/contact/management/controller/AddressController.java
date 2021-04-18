package com.contact.management.controller;

import com.contact.management.model.Address;
import com.contact.management.sdk.model.TransactionMessage;
import com.contact.management.sdk.response.ResponseSchema;
import com.contact.management.service.AddressService;
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
 * AddressController
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@RestController
@RequestMapping("/address")
public class AddressController {

  @Autowired
  public AddressService addressService;

  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Address>> create(
      @Valid @RequestBody Address address,
      BindingResult result) {
    ResponseSchema<Address> response = addressService.create(address);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Address>> update(
      @Valid @RequestBody Address address,
      BindingResult result) {
    ResponseSchema<Address> response = addressService.update(address);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<TransactionMessage>> delete(
      @RequestParam(value = "id", required = false, defaultValue = "0") BigInteger id) {
    ResponseSchema<TransactionMessage> response = addressService.delete(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping(value = "/deleteSoft", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Address>> deleteSoft(
      @RequestParam(value = "id", required = false, defaultValue = "0") BigInteger id) {
    ResponseSchema<Address> response = addressService.deleteSoft(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(value = "/getId", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<Address>> getId(
      @RequestParam(value = "id", required = false, defaultValue = "0") BigInteger id){
    ResponseSchema<Address> response = addressService.getId(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseSchema<List<Address>>> getAll() {
    ResponseSchema<List<Address>> response = addressService.getAll();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}

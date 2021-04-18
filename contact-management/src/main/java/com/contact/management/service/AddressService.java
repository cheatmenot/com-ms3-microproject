package com.contact.management.service;

import com.contact.management.entity.AddressEntity;
import com.contact.management.mapper.AddressMapper;
import com.contact.management.model.Address;
import com.contact.management.repository.AddressRepository;
import com.contact.management.sdk.model.TransactionMessage;
import com.contact.management.sdk.response.ResponseSchema;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * AddressEntity
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@Component
public class AddressService {

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  AddressMapper addressMapper;

  public ResponseSchema<Address> create(Address address){
    AddressEntity addressEntity = new AddressEntity();
    addressMapper.mapAddressToAddressEntity(address, addressEntity);
    addressEntity.setId(null);
    return saveEntity(addressEntity);
  }

  public ResponseSchema<Address> update(Address address){
    Optional<AddressEntity> findEntityOption =
        addressRepository.findByNotDeleted(address.getId());

    if(findEntityOption.isPresent()){
      AddressEntity addressEntity = new AddressEntity();
      addressMapper.mapAddressToAddressEntity(address, addressEntity);
      return saveEntity(addressEntity);
    }
    else {
      throw new RuntimeException("Cannot find id: " + address.getId() + " on Address");
    }
  }

  public ResponseSchema<TransactionMessage> delete(BigInteger id){
      Optional<AddressEntity> findEntityOption = addressRepository.findByNotDeleted(id);

      if(findEntityOption.isPresent()){
        ResponseSchema<TransactionMessage> resp = new ResponseSchema<>();
        resp.setResult(new TransactionMessage("Successfully Deleted on Address with Id: " + id));
        addressRepository.deleteById(findEntityOption.get().getId());
        return resp;
      }
      else {
        throw new RuntimeException("Cannot find id: " + id + " on Address");
      }
    }

  public ResponseSchema<Address> deleteSoft(BigInteger id){
    Optional<AddressEntity> findEntityOption = addressRepository.findByNotDeleted(id);

    if(findEntityOption.isPresent()){
      AddressEntity addressEntity = findEntityOption.get();
      addressEntity.setStatus("DEL");
      addressEntity.setModifiedBy("x");
      return saveEntity(addressEntity);
    }
    else {
      throw new RuntimeException("Cannot find id: " + id + " on Address");
    }
  }

  public ResponseSchema<Address> getId(BigInteger id){
    Optional<AddressEntity> findEntityOption = addressRepository.findByNotDeleted(id);

    if(findEntityOption.isPresent()){
      Address result = addressMapper.mapAddressEntityToAddress(findEntityOption.get());
      ResponseSchema<Address> resp = new ResponseSchema<>();
      resp.setStatus(HttpStatus.OK);
      resp.setResult(result);
      return resp;
    }
    else {
      throw new RuntimeException("Cannot find id: " + id + " on Address");
    }
  }

  public ResponseSchema<List<Address>> getAll(){
    List<AddressEntity> findEntityList = addressRepository.findAll();
    List<Address> resultList = new ArrayList<>();

    if(!findEntityList.isEmpty()){
      findEntityList.forEach(entity -> {
        Address resultIndiv = addressMapper.mapAddressEntityToAddress(entity);
        resultList.add(resultIndiv);
      });
    }

    ResponseSchema<List<Address>> resp = new ResponseSchema<>();
    resp.setStatus(HttpStatus.OK);
    resp.setResult(resultList);
    return resp;
  }

  private ResponseSchema<Address> saveEntity(AddressEntity addressEntity){
    AddressEntity savedEntity = addressRepository.save(addressEntity);

    Address result = addressMapper.mapAddressEntityToAddress(savedEntity);
    ResponseSchema<Address> resp = new ResponseSchema<>();
    resp.setStatus(HttpStatus.OK);
    resp.setResult(result);
    return resp;
  }
}

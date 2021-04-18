package com.contact.management.service;

import com.contact.management.entity.IdentificationEntity;
import com.contact.management.mapper.IdentificationMapper;
import com.contact.management.model.Identification;
import com.contact.management.repository.IdentificationRepository;
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
 * IdentificationEntity
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@Component
public class IdentificationService {

  @Autowired
  IdentificationRepository identificationRepository;

  @Autowired
  IdentificationMapper identificationMapper;

  public ResponseSchema<Identification> create(Identification identification){
    IdentificationEntity identificationEntity = new IdentificationEntity();
    identificationMapper.mapIdentificationToIdentificationEntity(identification, identificationEntity);
    identificationEntity.setId(null);
    return saveEntity(identificationEntity);
  }

  public ResponseSchema<Identification> update(Identification identification){
    Optional<IdentificationEntity> findEntityOption =
        identificationRepository.findByNotDeleted(identification.getId());

    if(findEntityOption.isPresent()){
      IdentificationEntity identificationEntity = new IdentificationEntity();
      identificationMapper.mapIdentificationToIdentificationEntity(identification, identificationEntity);
      return saveEntity(identificationEntity);
    }
    else {
      throw new RuntimeException("Cannot find id: " + identification.getId() + " on Identification");
    }
  }

  public ResponseSchema<TransactionMessage> delete(BigInteger id){
      Optional<IdentificationEntity> findEntityOption = identificationRepository.findByNotDeleted(id);

      if(findEntityOption.isPresent()){
        ResponseSchema<TransactionMessage> resp = new ResponseSchema<>();
        resp.setResult(new TransactionMessage("Successfully Deleted on Identification with Id: " + id));
        identificationRepository.deleteById(findEntityOption.get().getId());
        return resp;
      }
      else {
        throw new RuntimeException("Cannot find id: " + id + " on Identification");
      }
    }

  public ResponseSchema<Identification> deleteSoft(BigInteger id){
    Optional<IdentificationEntity> findEntityOption = identificationRepository.findByNotDeleted(id);

    if(findEntityOption.isPresent()){
      IdentificationEntity identificationEntity = findEntityOption.get();
      identificationEntity.setStatus("DEL");
      identificationEntity.setModifiedBy("x");
      return saveEntity(identificationEntity);
    }
    else {
      throw new RuntimeException("Cannot find id: " + id + " on Identification");
    }
  }

  public ResponseSchema<Identification> getId(BigInteger id){
    Optional<IdentificationEntity> findEntityOption = identificationRepository.findByNotDeleted(id);

    if(findEntityOption.isPresent()){
      Identification result = identificationMapper.mapIdentificationEntityToIdentification(findEntityOption.get());
      ResponseSchema<Identification> resp = new ResponseSchema<>();
      resp.setStatus(HttpStatus.OK);
      resp.setResult(result);
      return resp;
    }
    else {
      throw new RuntimeException("Cannot find id: " + id + " on Identification");
    }
  }

  public ResponseSchema<List<Identification>> getAll(){
    List<IdentificationEntity> findEntityList = identificationRepository.findAll();
    List<Identification> resultList = new ArrayList<>();

    if(!findEntityList.isEmpty()){
      findEntityList.forEach(entity -> {
        Identification resultIndiv = identificationMapper.mapIdentificationEntityToIdentification(entity);
        resultList.add(resultIndiv);
      });
    }

    ResponseSchema<List<Identification>> resp = new ResponseSchema<>();
    resp.setStatus(HttpStatus.OK);
    resp.setResult(resultList);
    return resp;
  }

  private ResponseSchema<Identification> saveEntity(IdentificationEntity identificationEntity){
    IdentificationEntity savedEntity = identificationRepository.save(identificationEntity);

    Identification result = identificationMapper.mapIdentificationEntityToIdentification(savedEntity);
    ResponseSchema<Identification> resp = new ResponseSchema<>();
    resp.setStatus(HttpStatus.OK);
    resp.setResult(result);
    return resp;
  }
}

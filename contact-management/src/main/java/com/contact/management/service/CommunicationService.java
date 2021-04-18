package com.contact.management.service;

import com.contact.management.entity.CommunicationEntity;
import com.contact.management.mapper.CommunicationMapper;
import com.contact.management.model.Communication;
import com.contact.management.repository.CommunicationRepository;
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
 * CommunicationEntity
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@Component
public class CommunicationService {

  @Autowired
  CommunicationRepository communicationRepository;

  @Autowired
  CommunicationMapper communicationMapper;

  public ResponseSchema<Communication> create(Communication communication){
    CommunicationEntity communicationEntity = new CommunicationEntity();
    communicationMapper.mapCommunicationToCommunicationEntity(communication, communicationEntity);
    communicationEntity.setId(null);
    return saveEntity(communicationEntity);
  }

  public ResponseSchema<Communication> update(Communication communication){
    Optional<CommunicationEntity> findEntityOption =
        communicationRepository.findByNotDeleted(communication.getId());

    if(findEntityOption.isPresent()){
      CommunicationEntity communicationEntity = new CommunicationEntity();
      communicationMapper.mapCommunicationToCommunicationEntity(communication, communicationEntity);
      return saveEntity(communicationEntity);
    }
    else {
      throw new RuntimeException("Cannot find id: " + communication.getId() + " on Communication");
    }
  }

  public ResponseSchema<TransactionMessage> delete(BigInteger id){
      Optional<CommunicationEntity> findEntityOption = communicationRepository.findByNotDeleted(id);

      if(findEntityOption.isPresent()){
        ResponseSchema<TransactionMessage> resp = new ResponseSchema<>();
        resp.setResult(new TransactionMessage("Successfully Deleted on Communication with Id: " + id));
        communicationRepository.deleteById(findEntityOption.get().getId());
        return resp;
      }
      else {
        throw new RuntimeException("Cannot find id: " + id + " on Communication");
      }
    }

  public ResponseSchema<Communication> deleteSoft(BigInteger id){
    Optional<CommunicationEntity> findEntityOption = communicationRepository.findByNotDeleted(id);

    if(findEntityOption.isPresent()){
      CommunicationEntity communicationEntity = findEntityOption.get();
      communicationEntity.setStatus("DEL");
      communicationEntity.setModifiedBy("x");
      return saveEntity(communicationEntity);
    }
    else {
      throw new RuntimeException("Cannot find id: " + id + " on Communication");
    }
  }

  public ResponseSchema<Communication> getId(BigInteger id){
    Optional<CommunicationEntity> findEntityOption = communicationRepository.findByNotDeleted(id);

    if(findEntityOption.isPresent()){
      Communication result = communicationMapper.mapCommunicationEntityToCommunication(findEntityOption.get());
      ResponseSchema<Communication> resp = new ResponseSchema<>();
      resp.setStatus(HttpStatus.OK);
      resp.setResult(result);
      return resp;
    }
    else {
      throw new RuntimeException("Cannot find id: " + id + " on Communication");
    }
  }

  public ResponseSchema<List<Communication>> getAll(){
    List<CommunicationEntity> findEntityList = communicationRepository.findAll();
    List<Communication> resultList = new ArrayList<>();

    if(!findEntityList.isEmpty()){
      findEntityList.forEach(entity -> {
        Communication resultIndiv = communicationMapper.mapCommunicationEntityToCommunication(entity);
        resultList.add(resultIndiv);
      });
    }

    ResponseSchema<List<Communication>> resp = new ResponseSchema<>();
    resp.setStatus(HttpStatus.OK);
    resp.setResult(resultList);
    return resp;
  }

  private ResponseSchema<Communication> saveEntity(CommunicationEntity communicationEntity){
    CommunicationEntity savedEntity = communicationRepository.save(communicationEntity);

    Communication result = communicationMapper.mapCommunicationEntityToCommunication(savedEntity);
    ResponseSchema<Communication> resp = new ResponseSchema<>();
    resp.setStatus(HttpStatus.OK);
    resp.setResult(result);
    return resp;
  }
}

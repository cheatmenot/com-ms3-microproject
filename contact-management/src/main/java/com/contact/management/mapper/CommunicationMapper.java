package com.contact.management.mapper;

import com.contact.management.sdk.mapper.AbstractObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Component;

import com.contact.management.model.Communication;
import com.contact.management.entity.CommunicationEntity;

/**
 * CommunicationMapper
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@Component
public class CommunicationMapper extends AbstractObjectMapper {

    private ModelMapper modelMapper;

    public CommunicationMapper(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
            .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
    }

    public Communication mapCommunicationEntityToCommunication(CommunicationEntity communicationEntity){
        return map(communicationEntity, Communication.class);
    }

    public void mapCommunicationToCommunicationEntity(Communication communication, CommunicationEntity communicationEntity){
        map(communication, communicationEntity);
    }

    @Override
    protected ModelMapper getModelMapper(){
        return modelMapper;
    }

    protected void setModelMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
}

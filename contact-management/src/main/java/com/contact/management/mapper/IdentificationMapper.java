package com.contact.management.mapper;

import com.contact.management.sdk.mapper.AbstractObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Component;

import com.contact.management.model.Identification;
import com.contact.management.entity.IdentificationEntity;

/**
 * IdentificationMapper
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@Component
public class IdentificationMapper extends AbstractObjectMapper {

    private ModelMapper modelMapper;

    public IdentificationMapper(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
            .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
    }

    public Identification mapIdentificationEntityToIdentification(IdentificationEntity identificationEntity){
        return map(identificationEntity, Identification.class);
    }

    public void mapIdentificationToIdentificationEntity(Identification identification, IdentificationEntity identificationEntity){
        map(identification, identificationEntity);
    }

    @Override
    protected ModelMapper getModelMapper(){
        return modelMapper;
    }

    protected void setModelMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
}

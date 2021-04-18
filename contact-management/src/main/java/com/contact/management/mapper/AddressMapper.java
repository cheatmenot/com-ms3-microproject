package com.contact.management.mapper;

import com.contact.management.sdk.mapper.AbstractObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Component;

import com.contact.management.model.Address;
import com.contact.management.entity.AddressEntity;

/**
 * AddressMapper
 *
 * @author	rumel
 * @version	0.1
 * @since	1.0.0
 *
 */
@Component
public class AddressMapper extends AbstractObjectMapper {

    private ModelMapper modelMapper;

    public AddressMapper(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
            .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
    }

    public Address mapAddressEntityToAddress(AddressEntity addressEntity){
        return map(addressEntity, Address.class);
    }

    public void mapAddressToAddressEntity(Address address, AddressEntity addressEntity){
        map(address, addressEntity);
    }

    @Override
    protected ModelMapper getModelMapper(){
        return modelMapper;
    }

    protected void setModelMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
}

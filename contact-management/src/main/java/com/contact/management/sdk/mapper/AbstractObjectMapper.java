package com.contact.management.sdk.mapper;

import org.modelmapper.ModelMapper;

/**
 * Common mapping methods.
 */
public abstract class AbstractObjectMapper {

  /**
   * Get ModelMapper.
   * @return modelMapper
   */
  protected abstract ModelMapper getModelMapper();

  /**
   * Map input bean to a new output bean.
   * @param input Input bean
   * @param outputClass Output bean class
   * @return New output bean
   */
  protected <I, O> O map(I input, Class<O> outputClass) {
    return getModelMapper().map(input, outputClass);
  }

  /**
   * Map input bean to an existing output bean.
   * @param input Input bean
   * @param output Output bean
   */
  protected <I, O> void map(I input, O output) {
    getModelMapper().map(input, output);
  }
}

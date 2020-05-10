package com.mockito.demo.converter;

import org.apache.commons.beanutils.BeanUtils;
import lombok.SneakyThrows;

public class Converter<D, S> {

  @SneakyThrows
  public D convert(D destination, S source) {
      BeanUtils.copyProperties(destination, source);
      return destination;
  }
  
}

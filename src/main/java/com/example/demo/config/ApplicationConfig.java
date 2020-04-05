package com.example.demo.config;

import com.example.demo.parser.JsonParser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
  @Bean
  public JsonParser jsonParser() {
    return new JsonParser();
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}

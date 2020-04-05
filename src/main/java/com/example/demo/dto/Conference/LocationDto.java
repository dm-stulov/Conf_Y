package com.example.demo.dto.Conference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class LocationDto {
  @Getter @Setter
  private final String city;
  @Getter @Setter
  private final String country;
}

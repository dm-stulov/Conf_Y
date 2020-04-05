package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String city;
  private String country;
}

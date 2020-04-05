package com.example.demo.domain;

import com.example.demo.dto.participant.ParticipantStatus;
import com.example.demo.dto.participant.ParticipantType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Participant {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private ParticipantType type;
  private ParticipantStatus status;
  private boolean invited;
}

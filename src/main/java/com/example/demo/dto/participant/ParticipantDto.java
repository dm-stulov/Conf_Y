package com.example.demo.dto.participant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantDto {
  private ParticipantType type;
  private ParticipantStatus status;
  private boolean invited;
}


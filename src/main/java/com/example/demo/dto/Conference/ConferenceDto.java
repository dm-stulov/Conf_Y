package com.example.demo.dto.Conference;

import com.example.demo.dto.participant.ParticipantDto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@ToString
public class ConferenceDto {
  private final String _id;
  private final String title;
  private final Set<String> projects;
  private final LocationDto location;
  private final Set<String> tags;
  private final LocalDateTime dateStart;
  private final LocalDateTime dateFinish;
  private final Map<String, ParticipantDto> participants;
  private final String ytLink;
  private final Long attendance;
  private final String link;
  private final List<String> comments;
  private final ConferenceStatus status;
}

package com.example.demo.domain;

import com.example.demo.dto.Conference.ConferenceStatus;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Entity
public class Conference {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String _id;
  private String title;

  @ElementCollection
  private Set<String> projects;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Location location;

  @ElementCollection
  private Set<String> tags;

  private LocalDateTime dateStart;
  private LocalDateTime dateFinish;

  @MapKey
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Map<String, Participant> participants;

  private String ytLink;
  private Long attendance;
  private String link;
  @ElementCollection
  private List<String> comments;

  @Enumerated(EnumType.STRING)
  private ConferenceStatus status;
}
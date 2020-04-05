package com.example.demo.service;

import com.example.demo.domain.Conference;
import com.example.demo.dto.Conference.ConferenceDto;
import com.example.demo.dto.Conference.ConferenceStatus;
import com.example.demo.parser.JsonParser;
import com.example.demo.repository.ConferenceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ConferenceService {
  private final JsonParser jsonParser;
  private final ConferenceRepository conferenceRepository;
  private final ModelMapper modelMapper;

  public ConferenceService(
      JsonParser jsonParser,
      ConferenceRepository conferenceRepository,
      ModelMapper modelMapper
  ) {
    this.jsonParser = jsonParser;
    this.conferenceRepository = conferenceRepository;
    this.modelMapper = modelMapper;
  }

  public List<Conference> getAll() {
    return StreamSupport
        .stream(conferenceRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  public Conference getById(Long id) {
    return conferenceRepository.findById(id).get();
  }

  public Conference getByTitle(String title) {
    return conferenceRepository.findByTitle(title);
  }

  public List<Conference> getByTag(String tag) {
    return conferenceRepository.findByTags(tag);
  }

  public List<Conference> getByStatus(ConferenceStatus status) {
    return conferenceRepository.findByStatus(status);
  }

  public void saveConferences(List<ConferenceDto> conferences) {
    conferences.stream().forEach(this::saveConference);

  }

  public Conference saveConference(ConferenceDto conferenceDto) {
    Conference conference = modelMapper.map(conferenceDto, Conference.class);

    return conferenceRepository.save(conference);
  }

  @EventListener
  public void seedDatabase(ContextRefreshedEvent event) {
    var conferences = jsonParser.getConferences();
    saveConferences(conferences);
  }
}

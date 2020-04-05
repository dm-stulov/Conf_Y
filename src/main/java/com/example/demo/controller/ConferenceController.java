package com.example.demo.controller;

import com.example.demo.domain.Conference;
import com.example.demo.dto.Conference.ConferenceStatus;
import com.example.demo.service.ConferenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("conference")
public class ConferenceController {
  private final ConferenceService conferenceService;

  public ConferenceController(ConferenceService conferenceService) {
    this.conferenceService = conferenceService;
  }

  @GetMapping
  public List<Conference> getAll() {
    return conferenceService.getAll();
  }

  @GetMapping("/id/{id}")
  public Conference getById(@PathVariable("id") Long id) {
   return conferenceService.getById(id);
  }

  @GetMapping("/title/{title}")
  public Conference getByTitle(@PathVariable("title") String title) {
    return conferenceService.getByTitle(title);
  }

  @GetMapping("/tag/{tag}")
  public List<Conference> getByTag(@PathVariable("tag") String tag) {
    return conferenceService.getByTag(tag);
  }

  @GetMapping("/status/{status}")
  public List<Conference> getByStatus(@PathVariable("status") ConferenceStatus status) {
    return conferenceService.getByStatus(status);
  }
}

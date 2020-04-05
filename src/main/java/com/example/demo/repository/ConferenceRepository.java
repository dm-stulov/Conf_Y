package com.example.demo.repository;

import com.example.demo.domain.Conference;
import com.example.demo.dto.Conference.ConferenceStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRepository extends CrudRepository<Conference, Long> {
  Conference findByTitle(String title);

  List<Conference> findByTags(String tag);

  List<Conference> findByStatus(ConferenceStatus status);
}

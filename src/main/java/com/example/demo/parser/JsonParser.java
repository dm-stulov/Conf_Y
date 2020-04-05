package com.example.demo.parser;

import com.example.demo.dto.Conference.ConferenceDto;
import com.example.demo.dto.Conference.ConferenceStatus;
import com.example.demo.dto.Conference.LocationDto;
import com.example.demo.dto.participant.ParticipantDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class JsonParser {
  @Value("${PATH_TO_CONFERENCES}")
  private String pathToJsonFile;

  private final JSONParser parser;

  public JsonParser() {
    parser = new JSONParser();
  }

  public List<ConferenceDto> getConferences() {
    List<ConferenceDto> conferences = new ArrayList<>();
    try {
      JSONArray conferencesJson = getJsonObjects(Path.of(pathToJsonFile));

      conferencesJson.stream()
          .forEach(c -> conferences.add(parseConference((JSONObject) c)));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return conferences;
  }

  public JSONArray getJsonObjects(Path path) throws IOException, ParseException {
    JSONArray jsonArray;
    FileReader reader = new FileReader(path.toFile());

    jsonArray = (JSONArray) parser.parse(reader);

    return jsonArray;
  }

  private ConferenceDto parseConference(JSONObject conferenceJson) {
    final DateTimeFormatter isoDateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

    final String _id = (String) conferenceJson.get("_id");
    final String title = (String) conferenceJson.get("title");
    final Set<String> projects = new HashSet((List<String>) conferenceJson.get("projects"));
    final LocationDto location = new LocationDto(
        (String) ((JSONObject) conferenceJson.get("location")).get("city"),
        (String) ((JSONObject) conferenceJson.get("location")).get("country")
    );
    final Set<String> tags = new HashSet<>((List<String>) conferenceJson.get("tags"));
    final LocalDateTime dateStart = LocalDateTime.parse((String) conferenceJson.get("dateStart"), isoDateTimeFormatter);
    final LocalDateTime dateFinish = LocalDateTime.parse((String) conferenceJson.get("dateFinish"), isoDateTimeFormatter);
    final Map<String, ParticipantDto> participants = ((Map<String, ParticipantDto>) conferenceJson.get("participants"));
    final String ytLink = (String) conferenceJson.get("ytLink");
    final Long attendance = (Long) conferenceJson.get("attendance");
    final String link = (String) conferenceJson.get("link");
    final List<String> comments = (List<String>) conferenceJson.get("comments");
    final ConferenceStatus status = ConferenceStatus.valueOf((String) conferenceJson.get("status"));

    final ConferenceDto conference = ConferenceDto.builder()
        ._id(_id)
        .title(title)
        .projects(projects)
        .location(location)
        .tags(tags)
        .dateStart(dateStart)
        .dateFinish(dateFinish)
        .participants(participants)
        .ytLink(ytLink)
        .attendance(attendance)
        .link(link)
        .comments(comments)
        .status(status)
        .build();

    return conference;
  }
}

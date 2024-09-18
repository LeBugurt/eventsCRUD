package org.example.eventscrud.service;

import org.example.eventscrud.model.Event;
import org.example.eventscrud.web.controller.dto.EventDto;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Integer createEvent(EventDto eventDto);

    Optional<Event> getEventById(Integer id);

    List<Event> getAllEvents();

    Integer updateEvent(EventDto newEvent);

    void deleteEvent(Integer id);
}

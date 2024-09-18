package org.example.eventscrud.service.impl;

import lombok.AllArgsConstructor;
import org.example.eventscrud.exception.ResourceNotFoundException;
import org.example.eventscrud.model.Event;
import org.example.eventscrud.repository.EventRepository;
import org.example.eventscrud.service.EventService;
import org.example.eventscrud.service.mapper.EventMapper;
import org.example.eventscrud.web.controller.dto.EventDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;


    @Override
    public Integer createEvent(EventDto eventDto) {
        Event newEvent = eventMapper.toEvent(eventDto);
        return eventRepository.save(newEvent).getId();
    }

    @Override
    public Optional<Event> getEventById(Integer id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Integer updateEvent(EventDto eventDto) {
        if (eventRepository.existsById(eventDto.getId())) {
            return createEvent(eventDto);
        } else {
            throw new ResourceNotFoundException("Event not found with id " + eventDto.getId());
        }
    }

    @Override
    public void deleteEvent(Integer id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + id));

        eventRepository.delete(event);
    }
}

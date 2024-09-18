package org.example.eventscrud.service.mapper;

import org.example.eventscrud.model.Event;
import org.example.eventscrud.web.controller.dto.EventDto;
import org.springframework.stereotype.Component;

import org.example.eventscrud.exception.ValidationException;

@Component
public class EventMapper {

    private void validateString(String field, String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(field + " cannot consist only of whitespace");
        }
    }

    private void validateDto(EventDto eventDto) {
        validateString("name", eventDto.getName());
        validateString("place", eventDto.getPlace());
        validateString("email", eventDto.getEmail());
        validateString("applicant", eventDto.getApplicant());
    }

    public Event toEvent(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }

        validateDto(eventDto);
        Event event = new Event();

        event.setId(eventDto.getId());
        event.setName(eventDto.getName());
        event.setPlace(eventDto.getPlace());
        event.setComment(eventDto.getComment());
        event.setEmail(eventDto.getEmail());
        event.setApplicant(eventDto.getApplicant());
        event.setStartDate(eventDto.getStartDate());
        event.setEndDate(eventDto.getEndDate());
        event.setFullDay(eventDto.isFullDay());
        event.setOnlineEvent(eventDto.isOnlineEvent());
        event.setOutlookEvent(eventDto.isOutlookEvent());

        return event;
    }
}

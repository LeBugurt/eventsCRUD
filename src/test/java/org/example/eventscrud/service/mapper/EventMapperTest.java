package org.example.eventscrud.service.mapper;

import org.example.eventscrud.exception.ValidationException;
import org.example.eventscrud.model.Event;
import org.example.eventscrud.web.controller.dto.EventDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EventMapperTest {

    private EventMapper eventMapper;

    @BeforeEach
    void setUp() {
        eventMapper = Mockito.spy(new EventMapper());
    }

    @Test
    void toEvent_ShouldMapEventDtoToEvent() {
        EventDto eventDto = Mockito.spy(new EventDto());
        eventDto.setId(1);
        eventDto.setName("Test Name");
        eventDto.setPlace("Test Place");
        eventDto.setEmail("test@example.com");
        eventDto.setApplicant("test");
        eventDto.setStartDate(LocalDateTime.of(2024, 9, 20, 10, 0));
        eventDto.setEndDate(LocalDateTime.of(2024, 9, 20, 18, 0));
        eventDto.setFullDay(true);
        eventDto.setOnlineEvent(false);
        eventDto.setOutlookEvent(true);
        eventDto.setComment("Test Comment");

        Event event = eventMapper.toEvent(eventDto);

        assertNotNull(event);
        assertEquals(eventDto.getId(), event.getId());
        assertEquals(eventDto.getName(), event.getName());
        assertEquals(eventDto.getPlace(), event.getPlace());
        assertEquals(eventDto.getEmail(), event.getEmail());
        assertEquals(eventDto.getApplicant(), event.getApplicant());
        assertEquals(eventDto.getStartDate(), event.getStartDate());
        assertEquals(eventDto.getEndDate(), event.getEndDate());
        assertEquals(eventDto.isFullDay(), event.isFullDay());
        assertEquals(eventDto.isOnlineEvent(), event.isOnlineEvent());
        assertEquals(eventDto.isOutlookEvent(), event.isOutlookEvent());
        assertEquals(eventDto.getComment(), event.getComment());
    }

    @Test
    void toEvent_ShouldReturnNull_WhenEventDtoIsNull() {
        Event event = eventMapper.toEvent(null);

        assertNull(event);
    }

    @Test
    void toEvent_ShouldThrowValidationException_WhenNameIsEmpty() {
        EventDto eventDto = Mockito.spy(new EventDto());
        eventDto.setName("   ");
        eventDto.setPlace("Test Place");
        eventDto.setEmail("test@example.com");
        eventDto.setApplicant("test");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            eventMapper.toEvent(eventDto);
        });
        assertEquals("name cannot consist only of whitespace", exception.getMessage());
    }
}

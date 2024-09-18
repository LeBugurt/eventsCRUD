package org.example.eventscrud.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.eventscrud.web.controller.dto.EventDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EventControllerTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    @ExtendWith(MockitoExtension.class)
    void createEvent() throws Exception {
        EventDto eventDto = Mockito.spy(new EventDto());
        eventDto.setName("Test Event");
        eventDto.setPlace("Test Place");
        eventDto.setEmail("Test Email");
        eventDto.setApplicant("test@test.com");
        eventDto.setStartDate(LocalDateTime.of(2024, 9, 20, 10, 0, 0));
        eventDto.setEndDate(LocalDateTime.of(2024, 9, 20, 10, 0, 0));
        eventDto.setOnlineEvent(false);
        eventDto.setOutlookEvent(true);
        eventDto.setFullDay(true);

        mockMvc.perform(post("/events")
                                .content(objectMapper.writeValueAsString(eventDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void getEventById() throws Exception {
        this.mockMvc.perform(get("/events/1")).andExpectAll(
                status().isOk(),
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                content().json("""
                  {"id": 1, "name": "Test Event", "place": "Test Place", "email": "Test Email",
                  "applicant": "test@test.com", "startDate": "2024-09-20T10:00:00", "endDate": "2024-09-20T10:00:00",
                  "onlineEvent": false, "outlookEvent": true, "fullDay": true}
                 """)
        );
    }

    @Test
    @Order(3)
    public void testUpdateEvent() throws Exception {
        String updatedEventJson = "{\"id\": 1, \"name\": \"Test Update\", \"place\": \"Test Place\", \"email\": \"Test Email\"," +
                "\"applicant\": \"test@test.com\", \"startDate\": \"2024-09-20T10:00:00\", \"endDate\": \"2024-09-20T10:00:00\"," +
                "\"onlineEvent\": false, \"outlookEvent\": false, \"fullDay\": false}";

        mockMvc.perform(put("/events")
                        .contentType("application/json")
                        .content(updatedEventJson))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/events/1")).andExpectAll(
                status().isOk(),
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                content().json("""
                  {"id": 1, "name": "Test Update", "place": "Test Place", "email": "Test Email",
                  "applicant": "test@test.com", "startDate": "2024-09-20T10:00:00", "endDate": "2024-09-20T10:00:00",
                  "onlineEvent": false, "outlookEvent": false, "fullDay": false}
                 """)
        );
    }

    @Test
    @Order(4)
    public void testDeleteEvent() throws Exception {
        mockMvc.perform(delete("/events/1"))
                .andExpect(status().isNoContent());
    }
}
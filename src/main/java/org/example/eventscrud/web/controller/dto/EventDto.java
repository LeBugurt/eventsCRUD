package org.example.eventscrud.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class EventDto {

    private Integer id;

    private String name;

    private String place;

    private String comment;

    private String email;

    private String applicant;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @JsonProperty("is_full_day")
    private boolean fullDay;

    @JsonProperty("is_online_event")
    private boolean onlineEvent;

    @JsonProperty("is_outlook_event")
    private boolean outlookEvent;
}

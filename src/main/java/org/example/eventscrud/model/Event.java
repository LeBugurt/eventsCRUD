package org.example.eventscrud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "place")
    private String place;

    @Column(name = "comment")
    private String comment;

    @Column(name = "email")
    private String email;

    @Column(name = "applicant")
    private String applicant;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_full_day")
    private boolean fullDay;

    @Column(name = "is_online_event")
    private boolean onlineEvent;

    @Column(name = "is_outlook_event")
    private boolean outlookEvent;
}

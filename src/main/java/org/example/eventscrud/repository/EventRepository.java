package org.example.eventscrud.repository;

import org.example.eventscrud.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}

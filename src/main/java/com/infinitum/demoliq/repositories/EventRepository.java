package com.infinitum.demoliq.repositories;

import com.infinitum.demoliq.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
     List<Event> findAll();
}

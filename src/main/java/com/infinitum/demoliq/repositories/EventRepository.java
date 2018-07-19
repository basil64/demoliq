package com.infinitum.demoliq.repositories;

import com.infinitum.demoliq.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}

package com.infinitum.demoliq.services;

import com.infinitum.demoliq.entities.Event;
import com.infinitum.demoliq.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Iterable<Event> findAll() {
        return this.eventRepository.findAll();
    }

    public Event save(final Event event) {
        return this.eventRepository.save(event);
    }

    public Optional<Event> findById(final Long id) {
        return eventRepository.findById(id);
    }
}

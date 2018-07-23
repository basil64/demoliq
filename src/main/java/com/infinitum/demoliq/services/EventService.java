package com.infinitum.demoliq.services;

import com.infinitum.demoliq.entities.Event;
import com.infinitum.demoliq.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        List<Event> events = this.eventRepository.findAll();
        LOGGER.info("findAll [{}]", events);
        return events;
    }

    public Event save(final Event event) {
        return this.eventRepository.save(event);
    }

    public Event findById(final Long id) {
        Optional<Event> event = eventRepository.findById(id);
        LOGGER.info("findById id = [{}], event [{}]", id, event.orElse(null));
        return event.orElse(null);
    }
}

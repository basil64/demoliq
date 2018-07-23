package com.infinitum.demoliq.controllers;

import com.infinitum.demoliq.entities.Event;
import com.infinitum.demoliq.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/juno")
public class EventController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final EventService eventService;
    public static final String DEMOLIQ_APPLICATION = "Demoliq Application";

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("")
    public String healthCheck() {
        return DEMOLIQ_APPLICATION;
    }

    @GetMapping("/events")
    public Iterable<Event> findAll() {
        Iterable<Event> events = eventService.findAll();
        LOGGER.info("findAll [{}]", events);
        return events;
    }

    @GetMapping("/events/{id}")
    public Event findById(@PathVariable("id") final Long id) {
        return eventService.findById(id);
    }

    @PostMapping("/events")
    public ResponseEntity<Event> save(@RequestBody final Event event) {
        Event createdEvent = eventService.save(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }
}

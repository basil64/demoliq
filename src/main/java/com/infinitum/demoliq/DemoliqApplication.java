package com.infinitum.demoliq;

import com.infinitum.demoliq.entities.Event;
import com.infinitum.demoliq.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoliqApplication implements CommandLineRunner {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EventService eventService;

	public static void main(String[] args) {
		SpringApplication.run(DemoliqApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Event event = new Event(null, "Message one");
//		eventService.save(event);
//        LOGGER.info("event one [{}]", event);
//		event = new Event(null, "Message two");
//		eventService.save(event);
//        LOGGER.info("event two [{}]", event);
//		List<Event> events = eventService.findAll();
//        LOGGER.info("events [{}]", events);
	}
}

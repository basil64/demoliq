package com.infinitum.demoliq.repositories;

import com.infinitum.demoliq.entities.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
public class EventRepositoryTest {
    public static final String EVENT_MESSAGE = "Event message";
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void findAllTest() {
        Event event_1 = new Event(null, "Event 1");
        Event event_2 = new Event(null, "Event 2");
        entityManager.persist(event_1);
        entityManager.persist(event_2);
        Iterable<Event> events = eventRepository.findAll();
        assertThat(events).hasSize(2);
        for (Event event : events) {
            assertThat(event.getId()).isNotNull();
        }
    }

    @Test
    public void findByIdTest() {
        Event event = new Event(null, EVENT_MESSAGE);
        Event savedEvent = entityManager.persist(event);
        Optional<Event> foundEvent = eventRepository.findById(savedEvent.getId());
        assertThat(foundEvent).isNotEmpty();
        assertThat(foundEvent.get()).isEqualTo(savedEvent);
    }

    @Test
    public void saveTest() {
        Event event = new Event(null, EVENT_MESSAGE);
        Event savedEvent = eventRepository.save(event);
        assertThat(savedEvent.getId()).isNotNull();
        Event foundEvent = entityManager.find(Event.class, savedEvent.getId());
        assertThat(foundEvent).isNotNull();
        assertThat(foundEvent.getMessage()).isEqualTo(event.getMessage());
    }
}

package com.infinitum.demoliq.unit.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infinitum.demoliq.controllers.EventController;
import com.infinitum.demoliq.entities.Event;
import com.infinitum.demoliq.services.EventService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@Profile("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class EventControllerTest {
    private Logger LOGGER = LoggerFactory.getLogger(EventControllerTest.class);
    public static final long ID = 100L;
    public static final String MESSAGE = "Test Message";
    public static final int ID_VALUE = 100;
    private MockMvc mockMvc;

    @Mock
    private EventService eventService;
    private EventController eventController;
    private List<Event> events;
    private Event eventToSave;

    @Before
    public void before() {
        events = Arrays.asList(new Event[]{new Event(ID, MESSAGE)});
        eventToSave = new Event(null, MESSAGE);
        when(eventService.findAll()).thenReturn(events);
        when(eventService.save(eventToSave)).thenReturn(new Event(ID, MESSAGE));
        when(eventService.findById(ID)).thenReturn(new Event(ID, MESSAGE));
        eventController = new EventController(eventService);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    public void findAllTest() throws Exception {
        LOGGER.info("TEST findAllTest");
        String jasonString = asJsonString(events);
        mockMvc.perform(MockMvcRequestBuilders
            .get("/juno/events"))
            .andExpect(status().isOk())
            .andExpect(content().json(jasonString));
    }

    @Test
    public void saveTest() throws Exception {
        LOGGER.info("TEST saveTest");
        String jsonEvent = asJsonString(eventToSave);
        mockMvc.perform(MockMvcRequestBuilders
            .post("/juno/events")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(jsonEvent))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id", Matchers.is(100)))
            .andExpect(jsonPath("$.message", Matchers.is(MESSAGE)));
    }

    @Test
    public void testFindById() throws Exception {
        LOGGER.info("TEST testFindById");
        mockMvc.perform(MockMvcRequestBuilders
            .get("/juno/events/100"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id", Matchers.is(ID_VALUE)))
            .andExpect(jsonPath("$.message", Matchers.is(MESSAGE)));
    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}

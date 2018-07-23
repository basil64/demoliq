package com.infinitum.demoliq.integration.controllers;

import com.infinitum.demoliq.controllers.EventController;
import com.infinitum.demoliq.entities.Event;
import com.infinitum.demoliq.repositories.EventRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventControllerTest {

    public static final String EVENT_MESSAGE_TWO = "Event message two";
    public static final String EVENT_MESSAGE_ONE = "Event message one";

    @Autowired
    EventRepository eventRepository;

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }


    @Test
    public void testFindAll() {
        Event eventOne = new Event(null, EVENT_MESSAGE_ONE);
        Event eventTwo = new Event(null, EVENT_MESSAGE_TWO);
        eventRepository.save(eventOne);
        eventRepository.save(eventTwo);

        get("/juno/events")
            .then()
            .assertThat()
            .statusCode(200)
            .and()
            .body("size()", is(2));
    }

    @Test
    public void testFindById() {
        Event eventOne = new Event(null, EVENT_MESSAGE_ONE);
        eventRepository.save(eventOne);
        given()
            .contentType(ContentType.JSON)
            .pathParam("id", eventOne.getId())
        .when()
            .get("/juno/events/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .and()
            .body("id", equalTo(eventOne.getId().intValue()))
            .and()
            .body("message", is(eventOne.getMessage()));
    }

    @Test
    public void testSaveEvent() {
        String uri = "http://localhost:" + port + "/juno/events";
        RestAssured.baseURI = uri;
        given()
            .contentType("application/json")
            .body("{\"id\": \"\", \"message\": \"Event message\"}")
        .when()
            .post("")
        .then()
            .assertThat().statusCode(HttpStatus.CREATED.value())
            .and()
            .body("message", equalTo("Event message"))
            .and()
            .body("id", notNullValue());
    }
}

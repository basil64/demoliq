package com.infinitum.demoliq;

import com.infinitum.demoliq.entities.Event;
import com.infinitum.demoliq.repositories.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Profile("test")
public class DemoliqApplicationTests {

    @Autowired
    private EventRepository eventRepository;

	@Test
	public void contextLoads() {
	}

}

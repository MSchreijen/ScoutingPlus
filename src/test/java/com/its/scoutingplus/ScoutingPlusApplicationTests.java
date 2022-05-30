package com.its.scoutingplus;

import com.its.scoutingplus.controllers.PersonController;
import com.its.scoutingplus.services.interfaces.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ScoutingPlusApplicationTests {

    @Autowired
    private PersonController personController;

    @Autowired
    private PersonService personService;

    @Test
    void contextLoads() {
        assertThat(personController).isNotNull();
        assertThat(personService).isNotNull();
    }

}

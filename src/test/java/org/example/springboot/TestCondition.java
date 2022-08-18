package org.example.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles({"local"})
public class TestCondition {

    @Test
    @EnabledIf(
            expression = "${test1.enabled}",
            loadContext = true)
    void 테스트1() throws Exception {
        assertTrue(true);
    }

    @Test
    @EnabledIf(
            expression = "${test2.enabled}",
            loadContext = true)
    void 테스트2() throws Exception {
        assertTrue(true);
    }

}

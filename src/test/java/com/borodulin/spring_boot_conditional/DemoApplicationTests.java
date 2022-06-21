package com.borodulin.spring_boot_conditional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {
    private static String DEV_PROFILE = "Current profile is dev";
    private static String PROD_PROFILE = "Current profile is production";

    @Container
    private static GenericContainer<?> devApp = new GenericContainer<>(DockerImageName.parse("devapp:latest"))
            .withExposedPorts(8080);
    @Container
    private static GenericContainer<?> prodApp = new GenericContainer<>(DockerImageName.parse("prodapp:latest"))
            .withExposedPorts(8082);

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    public void contextDev() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8080) + "/profile", String.class);
        assertEquals(DEV_PROFILE, forEntity.getBody());
    }

    @Test
    void contextProd() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8082) + "/profile", String.class);
        assertEquals(PROD_PROFILE, forEntity.getBody());
    }
}

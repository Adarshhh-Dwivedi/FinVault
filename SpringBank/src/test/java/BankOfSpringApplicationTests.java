package com.example.SpringBank;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Standard Context Load Test for SpringBank.
 * This verifies that the Spring context, H2 database,
 * and all Beans initialize without issues.
 */
@SpringBootTest
class SpringBankApplicationTests {

    @Test
    void contextLoads() {
        // If the application starts up correctly, this test will pass.
    }
}
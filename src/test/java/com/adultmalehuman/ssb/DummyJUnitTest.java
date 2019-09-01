package com.adultmalehuman.ssb;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Duration;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test does not require the Spring context
 */
class DummyJUnitTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DummyJUnitTest.class);

	@BeforeAll
	static void beforeAll() {
		LOGGER.info("beforeAll() | INVOKED");
	}

	@AfterAll
	static void afterAll() {
		LOGGER.info("afterAll() | INVOKED");
	}

	DummyJUnitTest() {}

	@BeforeEach
	void beforeEach() {
		LOGGER.info("beforeEach() | INVOKED");
	}

	@AfterEach
	void afterEach() {
		LOGGER.info("afterEach() | INVOKED");
	}

	@Test
	void testXXX() {
		assertTimeout(Duration.ofSeconds(999), ()->{
			assertEquals(1, BigDecimal.ONE.intValue());
			assertTrue(Boolean.parseBoolean("true"));
		});
		LOGGER.info("Test passed! (big surprise)");
	}

}

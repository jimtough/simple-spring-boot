package com.jimtough.ssb;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Duration;

import com.jimtough.ssb.mvc.MyRestController;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * This test requires the Spring context
 */
@SpringBootTest
@ActiveProfiles(GlobalConstants.PROFILE_NAME_FOR_UNIT_TESTS)
class DummySpringBootUnitTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DummySpringBootUnitTest.class);

	@BeforeAll
	static void beforeAll() {
		LOGGER.info("beforeAll() | INVOKED");
	}

	@AfterAll
	static void afterAll() {
		LOGGER.info("afterAll() | INVOKED");
	}

	private final BeanFactory beanFactory;

	@Autowired
	DummySpringBootUnitTest(BeanFactory bf) {
		this.beanFactory = bf;
	}

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
			assertNotNull(beanFactory.getBean(MyRestController.class));
		});
		LOGGER.info("Test passed! (big surprise)");
	}

}

package com.jimtough.ssb.conf;

import javax.annotation.PostConstruct;

import com.jimtough.ssb.GlobalConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class TestingConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestingConfiguration.class);

	/**
	 * Configuration for integration and unit test runs
	 */
	@Profile({GlobalConstants.PROFILE_NAME_FOR_INTEGRATION_TESTS, GlobalConstants.PROFILE_NAME_FOR_UNIT_TESTS})
	@Configuration
	public static class TestingProfilesConfiguration {
		@PostConstruct
		void postConstruct() {
			LOGGER.info("Integration/Unit tests profile configuration activated");
		}
	}

}

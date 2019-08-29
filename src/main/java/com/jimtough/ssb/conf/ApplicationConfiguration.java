package com.jimtough.ssb.conf;

import javax.annotation.PostConstruct;

import com.jimtough.ssb.GlobalConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.context.annotation.ConditionalOnAwsCloudEnvironment;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApplicationConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

	/**
	 * Configuration for running on a native EC2 instance
	 */
	@Configuration
	@ConditionalOnAwsCloudEnvironment
	public static class AwsNativeConfiguration {
		@PostConstruct
		void postConstruct() {
			LOGGER.info("AWS environment detected - AWS native configuration activated");
		}
	}

	/**
	 * Configuration for local developer runs
	 */
	@Profile(GlobalConstants.PROFILE_NAME_FOR_LOCAL)
	@Configuration
	public static class LocalProfileConfiguration {
		@PostConstruct
		void postConstruct() {
			LOGGER.info("local profile configuration activated");
		}
	}

}

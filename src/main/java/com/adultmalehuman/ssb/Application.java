package com.adultmalehuman.ssb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.JvmMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.LogbackMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.web.servlet.WebMvcMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.web.tomcat.TomcatMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {
		JvmMetricsAutoConfiguration.class,
		LogbackMetricsAutoConfiguration.class,
		SystemMetricsAutoConfiguration.class,
		TomcatMetricsAutoConfiguration.class,
		WebMvcMetricsAutoConfiguration.class,
		SimpleMetricsExportAutoConfiguration.class,
		SystemMetricsAutoConfiguration.class })
public class Application {

	public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
		//
		//	LOGGER.info("Let's inspect the beans provided by Spring Boot:");
		//
		//	String[] beanNames = ctx.getBeanDefinitionNames();
		//	Arrays.sort(beanNames);
		//	for (String beanName : beanNames) {
		//		LOGGER.info("  --> bean name | [{}]", beanName);
		//	}
		//
		};
	}

}
package com.adultmalehuman.ssb.mvc;

import java.time.ZonedDateTime;
import java.util.Objects;

import com.adultmalehuman.ssb.metrics.MetricsReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyRestController.class);

	private static final String RESPONSE_HTML_TEMPLATE =
		"<html><body>" +
		"<h1>Greetings from my Spring Boot app!</h1>" +
		"<h2>Some info about this instance of my app</h2>" +
		"<ul>" +
		"<li>total page hits since startup: <b>%d</b></li>" +
		"<li>current server time: <b>%tc</b></li>" +
		"</ul>" +
		"<p><small>app version: %s</small></p>" +
		"</body></html>";

	@Value("${app.version}")
	private String appVersionString;

	private final MetricsReporter metricsReporter;

	MyRestController(MetricsReporter metricsReporter) {
		this.metricsReporter = Objects.requireNonNull(metricsReporter);
	}

	static final String CANNED_REPLY_STRING = "Greetings from my Spring Boot app!";

	@RequestMapping("/")
	public String index() {
		LOGGER.debug("Request received");
		final int pageHits = metricsReporter.incrementAndGetRequestCountTotal();
		return String.format(RESPONSE_HTML_TEMPLATE, pageHits, ZonedDateTime.now(), appVersionString);
	}

}

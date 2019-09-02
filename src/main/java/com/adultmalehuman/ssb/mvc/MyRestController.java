package com.adultmalehuman.ssb.mvc;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import com.adultmalehuman.ssb.GlobalConstants;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
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

	private final Random random;
	private final AtomicInteger requestCountTotal;
	private final AtomicBoolean randomBooleanValueA;
	private final AtomicBoolean randomBooleanValueB;
	private final AtomicBoolean randomBooleanValueC;
	private final AtomicBoolean randomBooleanValueD;
	private final AtomicReference<Double> randomDoubleReference;

	private static double atomicBooleanToDouble(final AtomicBoolean ab) {
		boolean b = Objects.requireNonNull(ab).get();
		return b ? 1 : 0;
	}

	MyRestController(MeterRegistry meterRegistry) {
		this.random = new Random();
		Objects.requireNonNull(meterRegistry, "meterRegistry cannot be null");
		this.requestCountTotal = meterRegistry.gauge(
				GlobalConstants.METRIC_PAGEHITS_TOTAL_COUNT,
				new AtomicInteger(0));
		this.randomBooleanValueA = meterRegistry.gauge(
				GlobalConstants.METRIC_RANDOM_BOOLEAN,
				Arrays.asList(
					Tag.of(GlobalConstants.DIMENSION_PHOENETIC, "alpha"),
					Tag.of(GlobalConstants.DIMENSION_ZABRABOOF, "foo")
				),
				new AtomicBoolean(false),
				MyRestController::atomicBooleanToDouble);
		this.randomBooleanValueB = meterRegistry.gauge(
				GlobalConstants.METRIC_RANDOM_BOOLEAN,
				Arrays.asList(
					Tag.of(GlobalConstants.DIMENSION_PHOENETIC, "alpha"),
					Tag.of(GlobalConstants.DIMENSION_ZABRABOOF, "bar")
				),
				new AtomicBoolean(false),
				MyRestController::atomicBooleanToDouble);
		this.randomBooleanValueC = meterRegistry.gauge(
				GlobalConstants.METRIC_RANDOM_BOOLEAN,
				Arrays.asList(
					Tag.of(GlobalConstants.DIMENSION_PHOENETIC, "baker"),
					Tag.of(GlobalConstants.DIMENSION_ZABRABOOF, "foo")
				),
				new AtomicBoolean(false),
				MyRestController::atomicBooleanToDouble);
		this.randomBooleanValueD = meterRegistry.gauge(
				GlobalConstants.METRIC_RANDOM_BOOLEAN,
				Arrays.asList(
					Tag.of(GlobalConstants.DIMENSION_PHOENETIC, "baker"),
					Tag.of(GlobalConstants.DIMENSION_ZABRABOOF, "bar")
				),
				new AtomicBoolean(false),
				MyRestController::atomicBooleanToDouble);
		this.randomDoubleReference = meterRegistry.gauge(
				GlobalConstants.METRIC_RANDOM_DOUBLE,
				Collections.singletonList(
					Tag.of(GlobalConstants.DIMENSION_PHOENETIC, "charlie")
				),
				new AtomicReference<>((double) 0),
				AtomicReference::get);
	}

	static final String CANNED_REPLY_STRING = "Greetings from my Spring Boot app!";

	@RequestMapping("/")
	public String index() {
		LOGGER.debug("Request received");
		randomBooleanValueA.set(random.nextBoolean());
		randomBooleanValueB.set(random.nextBoolean());
		randomBooleanValueC.set(random.nextBoolean());
		randomBooleanValueD.set(random.nextBoolean());
		randomDoubleReference.set(random.nextDouble());
		final int pageHits = requestCountTotal.incrementAndGet();
		return String.format(RESPONSE_HTML_TEMPLATE, pageHits, ZonedDateTime.now(), appVersionString);
	}

}

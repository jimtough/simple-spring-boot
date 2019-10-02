package com.adultmalehuman.ssb.metrics;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import com.adultmalehuman.ssb.GlobalConstants;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class MetricsReporterImpl implements MetricsReporter {

	// WARNING!
	// Metrics ended up costing me some money on AWS.
	// I didn't realize there is a low threshold on Cloudwatch metrics for the free tier.
	// Lessons learned:
	// - Do not publish too many metrics to Cloudwatch
	// - Do not publish metrics too often (every minute is probably too often)

	private final MeterRegistry meterRegistry;
	private final AtomicInteger requestCountTotal;

	@Autowired
	MetricsReporterImpl(MeterRegistry meterRegistry) {
		this.meterRegistry = Objects.requireNonNull(meterRegistry);
		this.requestCountTotal = meterRegistry.gauge(
				GlobalConstants.METRIC_PAGEHITS_TOTAL_COUNT,
				new AtomicInteger(0));
	}

	@Override
	public int incrementAndGetRequestCountTotal() {
		return requestCountTotal.incrementAndGet();
	}

}

package com.adultmalehuman.ssb.metrics;

/**
 * Published metrics to AWS Cloudwatch
 */
public interface MetricsReporter {

	/**
	 * Increment the counter and return the new value
	 * @return int
	 */
	int incrementAndGetRequestCountTotal();

}

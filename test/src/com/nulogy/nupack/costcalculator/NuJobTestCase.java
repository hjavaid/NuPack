package com.nulogy.nupack.costcalculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NuJobTestCase
{

	private static final double DELTA = 1e-15;

	@Before
	public void setUp() throws Exception
	{}

	@After
	public void tearDown() throws Exception
	{}

	@Test
	public void testThatDefaultFlatMarkupOnAllJobsIsCalculatedAsFivePercentOfTheBasePrice()
	{
		final double basePrice = 100;
		final NuJob nuJob = new NuJob(basePrice);
		assertEquals("The total price for the job should be 5", 5, nuJob.getTotalPrice(), DELTA);
	}

}

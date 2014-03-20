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
		final NuJob nuJob = new NuJob(basePrice, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
		final int totalPrice = 105;
		assertEquals("The total price for the job should be " + totalPrice, totalPrice, nuJob.getTotalPrice(), DELTA);
	}

	@Test
	public void testThatIfTheBasePriceIsZeroThenTheTotalPriceComesOutToZero()
	{
		final double basePrice = 0;
		final NuJob nuJob = new NuJob(basePrice, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
		assertEquals("The total price for the job should be 0", 0, nuJob.getTotalPrice(), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatIfANegavtiveBasePriceIsInputTheSystemThrowsAnException() throws Exception
	{
		final double basePrice = -1;
		@SuppressWarnings("unused")
		final NuJob nuJob = new NuJob(basePrice, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatIfNotANumberIsInputTheSystemThrowsAnException()
	{
		final double basePrice = Double.NaN;
		@SuppressWarnings("unused")
		final NuJob nuJob = new NuJob(basePrice, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThereShouldAtleastBeOnePersonAssignedPerJob()
	{
		final double basePrice = 100;
		@SuppressWarnings("unused")
		final NuJob nuJob = new NuJob(basePrice, 0);
	}
}

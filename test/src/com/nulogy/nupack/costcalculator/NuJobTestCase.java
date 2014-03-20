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
		final NuJob nuJob = new NuJob(basePrice, 1);
		final int totalPrice = 105;
		assertEquals("The total price for the job should be " + totalPrice, totalPrice, nuJob.getTotalPrice(), DELTA);
	}

	@Test
	public void testThatIfTheBasePriceIsZeroThenTheTotalPriceComesOutToZero() throws Exception
	{
		final double basePrice = 0;
		final NuJob nuJob = new NuJob(basePrice, 1);
		assertEquals("The total price for the job should be 0", 0, nuJob.getTotalPrice(), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatIfANegavtiveBasePriceIsInputTheSystemThrowsAnException() throws Exception
	{
		final double basePrice = -1;
		@SuppressWarnings("unused")
		final NuJob nuJob = new NuJob(basePrice, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatIfNotANumberIsInputTheSystemThrowsAnException() throws Exception
	{
		final double basePrice = Double.NaN;
		@SuppressWarnings("unused")
		final NuJob nuJob = new NuJob(basePrice, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThereShouldAtleastBeOnePersonAssignedPerJob() throws Exception
	{
		final double basePrice = 100;
		final NuJob nuJob = new NuJob(basePrice, 0);

	}

}

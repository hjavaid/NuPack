package com.nulogy.nupack.costcalculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NuJobTestCase
{

	private static final double DELTA = 1e-15;
	private static final double BASE_PRICE = 100;
	private NuJob nuJob;

	@Before
	public void setUp() throws Exception
	{

	}

	@After
	public void tearDown() throws Exception
	{

	}

	private final NuJob createNuJob(double basePrice, int numberOfPersonsWorking)
	{
		nuJob = new NuJob(basePrice, numberOfPersonsWorking);
		return nuJob;
	}

	private double totalPriceWithFlatAndPersonMarkup(final double basePrice, final int numberOfWorkingPersons)
	{
		final double basePriceAfterDefaultMarkup = basePrice + basePrice * NuJob.DEFAULT_FLAT_MARKUP_PERCENTAGE_IN_DECIMAL;
		return basePriceAfterDefaultMarkup + (NuJob.MARKUP_RATIO_PERSONS_WORKING_IN_DECIMAL * numberOfWorkingPersons * basePriceAfterDefaultMarkup);
	}

	@Test
	public void testThatDefaultFlatMarkupOnAllJobsIsCalculatedAsFivePercentOfTheBasePrice()
	{
		nuJob = createNuJob(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
		final double expectedTotalPrice = totalPriceWithFlatAndPersonMarkup(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
		assertEquals("The total price for the job should be " + expectedTotalPrice, expectedTotalPrice, nuJob.getTotalPrice(), DELTA);
	}

	@Test
	public void testThatIfTheBasePriceIsZeroThenTheTotalPriceComesOutToZero()
	{
		final double basePrice = 0;
		nuJob = createNuJob(basePrice, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
		assertEquals("The total price for the job should be 0", 0, nuJob.getTotalPrice(), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatIfANegavtiveBasePriceIsInputTheSystemThrowsAnException()
	{
		final double basePrice = -1;
		nuJob = createNuJob(basePrice, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatIfNotANumberIsInputTheSystemThrowsAnException()
	{
		final double basePrice = Double.NaN;
		nuJob = createNuJob(basePrice, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThereShouldAtleastBeOnePersonAssignedPerJob()
	{
		@SuppressWarnings("unused")
		final NuJob nuJob = new NuJob(BASE_PRICE, 0);
	}

	@Test
	public void testThatIfOnePersonWorksOnTheJobTheSystemCalculatesTheMarkupOnBasePricePlusFlatMarkup()
	{
		nuJob = createNuJob(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
		final double expectedTotalPrice = totalPriceWithFlatAndPersonMarkup(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
		assertEquals("The total price for the job should be " + expectedTotalPrice, expectedTotalPrice, nuJob.getTotalPrice(), DELTA);
	}

	@Test
	public void testThatIfTwoPersonsWorksOnTheJobTheSystemCalculatesTheMarkupOnBasePricePlusFlatMarkup()
	{
		final int numberOfPersonsWorking = 2;
		nuJob = createNuJob(BASE_PRICE, numberOfPersonsWorking);
		final double expectedTotalPrice = totalPriceWithFlatAndPersonMarkup(BASE_PRICE, numberOfPersonsWorking);
		assertEquals("The total price for the job with 2 working persons should be " + expectedTotalPrice, expectedTotalPrice, nuJob.getTotalPrice(), DELTA);
	}
}

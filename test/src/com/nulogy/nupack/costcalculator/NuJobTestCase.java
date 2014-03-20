package com.nulogy.nupack.costcalculator;

import static org.junit.Assert.*;
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

	private final NuJob createNuJob(double basePrice, int numberOfPersonsWorking, String materialCategory)
	{
		nuJob = new NuJob(basePrice, numberOfPersonsWorking, materialCategory);
		return nuJob;
	}

	private double caclculateTotalPrice(final double basePrice, final int numberOfWorkingPersons, String materialCategory)
	{
		final double priceAfterFlatMarkup = basePrice + basePrice * NuJob.MARKUP_RATIO_FLAT_DECIMAL;
		double pharmaceuticalMarkup = 0;
		double foodMarkup = 0;
		double electronicsMarkup = 0;
		double personsWorkingMarkup = 0;

		if (materialCategory != null && !materialCategory.isEmpty())
		{
			if (NuJob.DRUGS.equalsIgnoreCase(nuJob.getMaterialCategory()))
			{
				pharmaceuticalMarkup = priceAfterFlatMarkup * NuJob.MARKUP_RATIO_PHARMACEUTICALS_DECIMAL;
			}
			else if (NuJob.FOOD.equalsIgnoreCase(nuJob.getMaterialCategory()))
			{
				foodMarkup = priceAfterFlatMarkup * NuJob.MARKUP_RATIO_FOOD_DECIMAL;
			}
			else if (NuJob.ELECTRONICS.equalsIgnoreCase(nuJob.getMaterialCategory()))
			{
				electronicsMarkup = priceAfterFlatMarkup * NuJob.MARKUP_RATIO_ELECTRONICS;
			}
		}
		personsWorkingMarkup = NuJob.MARKUP_RATIO_PERSONS_WORKING_DECIMAL * numberOfWorkingPersons * priceAfterFlatMarkup;

		return priceAfterFlatMarkup + pharmaceuticalMarkup + foodMarkup + electronicsMarkup + personsWorkingMarkup;
	}

	@Test
	public void testThatDefaultFlatMarkupOnAllJobsIsCalculatedAsFivePercentOfTheBasePrice()
	{
		nuJob = createNuJob(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB);
		final double expectedTotalPrice = caclculateTotalPrice(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB, "");
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
		final double expectedTotalPrice = caclculateTotalPrice(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB, "");
		assertEquals("The total price for the job should be " + expectedTotalPrice, expectedTotalPrice, nuJob.getTotalPrice(), DELTA);
	}

	@Test
	public void testThatIfTwoPersonsWorksOnTheJobTheSystemCalculatesTheMarkupOnBasePricePlusFlatMarkup()
	{
		final int numberOfPersonsWorking = 2;
		nuJob = createNuJob(BASE_PRICE, numberOfPersonsWorking);
		final double expectedTotalPrice = caclculateTotalPrice(BASE_PRICE, numberOfPersonsWorking, "");
		assertEquals("The total price for the job with 2 working persons should be " + expectedTotalPrice, expectedTotalPrice, nuJob.getTotalPrice(), DELTA);
	}

	@Test
	public void testThatForMaterialTypePharmaceuticalsTheSystemAddsOnTheRelevantMarkupInTotalPrice()
	{
		nuJob = createNuJob(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB, NuJob.DRUGS);
		final double expectedTotalPrice = caclculateTotalPrice(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB, NuJob.DRUGS);
		assertEquals("The total price for the job should be " + expectedTotalPrice, expectedTotalPrice, nuJob.getTotalPrice(), DELTA);
	}

	@Test
	public void testThatForMaterialTypeFoodTheSystemAddsOnTheRelevantMarkupInTotalPrice()
	{
		nuJob = createNuJob(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB, NuJob.FOOD);
		final double expectedTotalPrice = caclculateTotalPrice(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB, NuJob.FOOD);
		assertEquals("The total price for the job should be " + expectedTotalPrice, expectedTotalPrice, nuJob.getTotalPrice(), DELTA);
	}

	@Test
	public void testThatForMaterialTypeElectronicsTheSystemAddsOnTheRelevantMarkupInTotalPrice()
	{
		nuJob = createNuJob(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB, NuJob.ELECTRONICS);
		final double expectedTotalPrice = caclculateTotalPrice(BASE_PRICE, NuJob.MINIMUM_NUMBER_OF_PERSONS_PER_JOB, NuJob.ELECTRONICS);
		assertEquals("The total price for the job should be " + expectedTotalPrice, expectedTotalPrice, nuJob.getTotalPrice(), DELTA);
	}

	@Test
	public void testThatForOtherMaterialTypesTheSystemDoesnotAddAnyMarkupInTotalPrice() throws Exception
	{
		nuJob = createNuJob(12456.95, 4, "books");
		final double expectedTotalPrice = caclculateTotalPrice(12456.95, 4, "books");
		assertEquals("The total price for the job should be " + expectedTotalPrice, expectedTotalPrice, nuJob.getTotalPrice(), DELTA);
	}

	@Test
	public void testThatTheSystemCalculatesTheCorrectPriceByAlwaysAddingMarkupsOnBasePricePlusFlatMarkup() throws Exception
	{
		nuJob = createNuJob(5432.00, 4, NuJob.FOOD);
		double intentionallyWrongExpectedPrice = 6573.72;
		assertNotSame("The total price for the job should be " + intentionallyWrongExpectedPrice, intentionallyWrongExpectedPrice, nuJob.getTotalPrice());
	}
}

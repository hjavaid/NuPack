package com.nulogy.nupack.costcalculator;

public class NuJob
{

	protected static final int MINIMUM_NUMBER_OF_PERSONS_PER_JOB = 1;
	protected static final double DEFAULT_FLAT_MARKUP_PERCENTAGE_IN_DECIMAL = 0.05;
	private double basePrice;

	public NuJob(double basePrice, int numberOfPersons) throws IllegalArgumentException
	{
		super();
		if (basePrice != Math.abs(basePrice))
		{
			throw new IllegalArgumentException("Only a positive base price is allowed");
		}
		else if (numberOfPersons < MINIMUM_NUMBER_OF_PERSONS_PER_JOB)
		{
			throw new IllegalArgumentException("At least one person has to work on a job");
		}

		this.basePrice = basePrice;
	}

	public final double getTotalPrice()
	{
		return this.basePrice + this.basePrice * NuJob.DEFAULT_FLAT_MARKUP_PERCENTAGE_IN_DECIMAL;
	}

}

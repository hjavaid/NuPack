package com.nulogy.nupack.costcalculator;

public class NuJob
{

	protected static final double MARKUP_RATIO_PERSONS_WORKING_IN_DECIMAL = 1.2 / 100;
	protected static final int MINIMUM_NUMBER_OF_PERSONS_PER_JOB = 1;
	protected static final double DEFAULT_FLAT_MARKUP_PERCENTAGE_IN_DECIMAL = 0.05;
	protected static final double MARKUP_RATIO_PHARMACEUTICALS = 7.5 / 100;
	private double basePrice;
	private int numberOfPersonsWorking;
	private String materialCategory;

	public NuJob(double basePrice, int numberOfPersonsWorking) throws IllegalArgumentException
	{
		super();
		if (basePrice != Math.abs(basePrice))
		{
			throw new IllegalArgumentException("Only a positive base price is allowed");
		}
		else if (numberOfPersonsWorking < MINIMUM_NUMBER_OF_PERSONS_PER_JOB)
		{
			throw new IllegalArgumentException("At least one person has to work on a job");
		}

		this.basePrice = basePrice;
		this.numberOfPersonsWorking = numberOfPersonsWorking;
	}

	public NuJob(double basePrice, int numberOfPersonsWorking, String materialCategory)
	{
		this(basePrice, numberOfPersonsWorking);
		this.materialCategory = materialCategory;
	}

	public final double getTotalPrice()
	{
		double totalPrice = this.basePrice + this.basePrice * DEFAULT_FLAT_MARKUP_PERCENTAGE_IN_DECIMAL;
		if (this.materialCategory != null && !this.materialCategory.isEmpty())
		{
			if ("pharmaceuticals".equalsIgnoreCase(materialCategory))
			{
				totalPrice += totalPrice * NuJob.MARKUP_RATIO_PHARMACEUTICALS;
			}

		}
		totalPrice += totalPrice * MARKUP_RATIO_PERSONS_WORKING_IN_DECIMAL * numberOfPersonsWorking;
		return totalPrice;
	}

	public String getMaterialCategory()
	{
		return this.materialCategory;
	}

}

package com.nulogy.nupack.costcalculator;

public class NuJob
{

	protected static final String ELECTRONICS = "electronics";
	protected static final String FOOD = "food";
	protected static final String DRUGS = "drugs";
	protected static final double MARKUP_RATIO_PERSONS_WORKING_DECIMAL = 1.2 / 100;
	protected static final int MINIMUM_NUMBER_OF_PERSONS_PER_JOB = 1;
	protected static final double MARKUP_RATIO_FLAT_DECIMAL = 0.05;
	protected static final double MARKUP_RATIO_PHARMACEUTICALS_DECIMAL = 7.5 / 100;
	protected static final double MARKUP_RATIO_FOOD_DECIMAL = 13 / 100;
	protected static final double MARKUP_RATIO_ELECTRONICS = 2 / 100;
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
		final double priceAfterFlatMarkup = this.basePrice + this.basePrice * MARKUP_RATIO_FLAT_DECIMAL;
		double pharmaceuticalMarkup = 0;
		double foodMarkup = 0;
		double electronicsMarkup = 0;
		double personsWorkingMarkup = 0;

		if (this.materialCategory != null && !this.materialCategory.isEmpty())
		{
			if (DRUGS.equalsIgnoreCase(materialCategory))
			{
				pharmaceuticalMarkup = priceAfterFlatMarkup * MARKUP_RATIO_PHARMACEUTICALS_DECIMAL;
			}
			else if (FOOD.equalsIgnoreCase(materialCategory))
			{
				foodMarkup = priceAfterFlatMarkup * MARKUP_RATIO_FOOD_DECIMAL;
			}
			else if (ELECTRONICS.equalsIgnoreCase(materialCategory))
			{
				electronicsMarkup = priceAfterFlatMarkup * MARKUP_RATIO_ELECTRONICS;
			}

		}
		personsWorkingMarkup = priceAfterFlatMarkup * MARKUP_RATIO_PERSONS_WORKING_DECIMAL * numberOfPersonsWorking;
		return priceAfterFlatMarkup + pharmaceuticalMarkup + foodMarkup + electronicsMarkup + personsWorkingMarkup;
	}

	public String getMaterialCategory()
	{
		return this.materialCategory;
	}

}

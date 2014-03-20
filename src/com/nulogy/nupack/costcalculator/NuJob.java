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
		double markupFromMaterialTypes = 0;
		double personsWorkingMarkup = 0;

		if (this.materialCategory != null && !this.materialCategory.isEmpty())
		{
			markupFromMaterialTypes = calculateMarkups(priceAfterFlatMarkup);
		}
		personsWorkingMarkup = priceAfterFlatMarkup * MARKUP_RATIO_PERSONS_WORKING_DECIMAL * numberOfPersonsWorking;

		return priceAfterFlatMarkup + markupFromMaterialTypes + personsWorkingMarkup;
	}

	private final double calculateMarkups(final double priceAfterFlatMarkup)
	{
		double markupFromMaterialTypes = 0;

		if (DRUGS.equalsIgnoreCase(materialCategory))
		{
			markupFromMaterialTypes = priceAfterFlatMarkup * MARKUP_RATIO_PHARMACEUTICALS_DECIMAL;
		}
		else if (FOOD.equalsIgnoreCase(materialCategory))
		{
			markupFromMaterialTypes = priceAfterFlatMarkup * MARKUP_RATIO_FOOD_DECIMAL;
		}
		else if (ELECTRONICS.equalsIgnoreCase(materialCategory))
		{
			markupFromMaterialTypes = priceAfterFlatMarkup * MARKUP_RATIO_ELECTRONICS;
		}

		return markupFromMaterialTypes;
	}

	public String getMaterialCategory()
	{
		return this.materialCategory;
	}

	@Override
	public String toString()
	{
		final String materialCategory = this.materialCategory == null || this.materialCategory.isEmpty() ? "None" : this.materialCategory;
		return "[Base Price:" + this.basePrice + ";Number of persons working:" + this.numberOfPersonsWorking + ";Material Category:" + materialCategory + "]";
	}
}

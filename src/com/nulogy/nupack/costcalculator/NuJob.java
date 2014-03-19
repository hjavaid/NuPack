package com.nulogy.nupack.costcalculator;

public class NuJob
{

	protected static final double DEFAULT_FLAT_MARKUP_PERCENTAGE_IN_DECIMAL = 0.05;
	private double basePrice;

	public NuJob(double basePrice)
	{
		super();
		this.basePrice = basePrice;
	}

	public final double getTotalPrice()
	{
		return this.basePrice * NuJob.DEFAULT_FLAT_MARKUP_PERCENTAGE_IN_DECIMAL;
	}

}

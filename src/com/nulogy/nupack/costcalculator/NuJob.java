package com.nulogy.nupack.costcalculator;

public class NuJob
{

	private double basePrice;

	public NuJob(double basePrice)
	{
		super();
		this.basePrice = basePrice;
	}

	public double getTotalPrice()
	{
		return this.basePrice * 0.05;
	}

}

package com.fruits.app;

public class Orange extends Fruit
{
	public Orange()
	{
		this("orange",150.6d);
	}
	
	public Orange(String color, double weight)
	{
		super(color,weight,"Orange",true);
	}
	
	public String getColor() 
	{
		return super.getColor();
	}


	public void setColor(String color) 
	{
		super.setColor(color);
	}


	public double getWeight() 
	{
		return super.getWeight();
	}


	public void setWeight(double weight) 
	{
		super.setWeight(weight);
	}


	public String getName() 
	{
		return super.getName();
	}


	public void setName(String name) 
	{
		super.setName(name);
	}


	public boolean isFresh() 
	{
		return super.getisFresh();
	}


	public void setFresh(boolean isFresh) 
	{
		super.setisFresh(isFresh);
	}
	
	
	@Override
	public String taste()
	{
		return "sour";
	}

	@Override
	public String toString() {
		return "Orange [Color=" + getColor() + ", Weight=" + getWeight() + ", Name=" + getName()
				+ ", Fresh=" + isFresh() + ", taste=" + taste() + "]";
	}
	
	
}

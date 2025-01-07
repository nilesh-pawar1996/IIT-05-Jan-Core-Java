package com.fruits.app;

public class Mango extends Fruit
{
	public Mango()
	{
		this("yellow",350.4d);
	}
	
	public Mango(String color, double weight)
	{
		super(color,weight,"Mango",true);
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
		return "sweet";
	}

	@Override
	public String toString() {
		return "Mango [Color=" + getColor() + ", Weight=" + getWeight() + ", Name=" + getName()
				+ ", Fresh=" + isFresh() + ", taste()=" + taste() + "]";
	}
	
	
}

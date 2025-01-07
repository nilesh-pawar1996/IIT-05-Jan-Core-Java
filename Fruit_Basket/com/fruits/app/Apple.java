package com.fruits.app;

public class Apple extends Fruit
{
	public Apple()
	{
		this("red",250.8d);
	}
	
	public Apple(String color, double weight)
	{
		super(color,weight,"Apple",true);
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
	public String toString() {
		return "Apple [Color=" + getColor() + ", Weight=" + getWeight() + ", Name=" + getName()
				+ ", Fresh=" + isFresh() + ", taste=" + taste() + "]";
	}

	@Override
	public String taste()
	{
		return "sweet n sour";
	}
}

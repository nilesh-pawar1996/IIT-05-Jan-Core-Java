package com.fruits.app;

public class Fruit 
{
	private String color;
	private double weight;
	private String name;
	private boolean isFresh;
	
	
	public Fruit() 
	{
		this(null,0.0d,null,true);
	}


	public Fruit(String color, double weight, String name, boolean isFresh) 
	{
		super();
		this.color = color;
		this.weight = weight;
		this.name = name;
		this.isFresh = isFresh;
	}


	public String getColor() 
	{
		return this.color;
	}


	public void setColor(String color) 
	{
		this.color = color;
	}


	public double getWeight() 
	{
		return this.weight;
	}


	public void setWeight(double weight) 
	{
		this.weight = weight;
	}


	public String getName() 
	{
		return this.name;
	}


	public void setName(String name) 
	{
		this.name = name;
	}


	public boolean getisFresh() 
	{
		return this.isFresh;
	}


	public void setisFresh(boolean isFresh) 
	{
		this.isFresh = isFresh;
	}


	
	
	@Override
	public String toString() {
		return "Fruit [color=" + color + ", weight=" + weight + ", name=" + name + ", isFresh=" + isFresh + ", taste="
				+ taste() + "]";
	}


	public String taste()
	{
		return "no specific taste";
	}
	
	
}




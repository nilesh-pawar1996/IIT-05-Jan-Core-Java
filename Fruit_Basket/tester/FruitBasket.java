package tester;

import java.util.Scanner;

import  com.fruits.app.*;

public class FruitBasket 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter the size of basket");
		
		int n = sc.nextInt();
		
		Fruit[] f = new Fruit[n];
		
		int cnt = 0;
		
		int choice = 0;
		
		System.out.println("1. Add Mango");
		System.out.println("2. Add Apple");
		System.out.println("3. Add Orange");
		System.out.println("4. Display names of all fruits in the basket");
		System.out.println("5. Display name,color,weight , taste of all fresh fruits , in the basket");
		System.out.println("6. Display tastes of all stale(not fresh) fruits in the basket.");
		System.out.println("7. Mark a fruit as stale");
		System.out.println("8. Mark all sour fruits stale ");
		System.out.println("9. Exit");
		
		
		do
		{
			System.out.println("enter choice");
			choice  = sc.nextInt();
			
			switch(choice)
			{
			case 1: if(cnt<n)
					{
						System.out.println("enter the color of mango");
						String color  = sc.next();
						System.out.println("enter the weight of mango");
						double weight  = sc.nextDouble();
						f[cnt] = new Mango(color.toLowerCase().strip(), weight);
						cnt++;
					}
					else
					{
						System.out.println("basket full cannot add mango");
					}
				
					break;
					
			case 2:	if(cnt<n)
					{
						System.out.println("enter the color of Apple");
						String color  = sc.next();
						System.out.println("enter the weight of Apple");
						double weight  = sc.nextDouble();
						f[cnt] = new Apple(color.toLowerCase().strip(), weight);
						cnt++;
					}
					else
					{
						System.out.println("basket full cannot add Apple");
					}
		
					break;
					
			case 3: if(cnt<n)
					{
						System.out.println("enter the color of Orange");
						String color  = sc.next();
						System.out.println("enter the weight of Orange");
						double weight  = sc.nextDouble();
						f[cnt] = new Orange(color.toLowerCase().strip(), weight);
						cnt++;
					}
					else
					{
						System.out.println("basket full cannot add Orange");
					}
		
					break;
					
			case 4: for(Fruit e: f)
					{
						if(e != null)
						System.out.println(e.getName());
					}
					break;
					
			case 5: for(Fruit e: f)
					{
						if(e != null)
						System.out.println("color: "+e.getColor());
						System.out.println("weight: "+e.getWeight());
						System.out.println("name: "+e.getName());
						System.out.println("Fresh: "+e.getisFresh());
						System.out.println("Taste: "+e.taste());
						System.out.println(e);
					}
					break;
				
			case 6:	int cntr = 0;
					for(Fruit e: f)
					{
						if(e.getisFresh() == false)
						{
							System.out.println(e.taste());
							System.out.println(e);
							cntr++;
						}
					}
					if(cntr == 0)
					{
						System.out.println("no stale fruits present in the basket");
					}
					break;
					
			case 7:	System.out.println("enter the index for which you need to mark stale");
					int idx = sc.nextInt();
					if(idx >= 0 && idx <= n-1)
					{
						f[idx].setisFresh(false);
					}
					else
					{
						System.out.println("invalid index");
					}
			
					break;
					
			case 8: for(Fruit e: f)
					{
						String s1 = e.taste();
						s1 = s1.toLowerCase().strip();
						if(s1.equals("sour"))
						{
							e.setisFresh(false);
						}
					}
					System.out.println("all sour frutis ae marked as stale");
					break;
					
			default :System.out.println("select correct option");
			
			}
			
		}while(choice != 9);
		
		sc.close();
	}
	
	
}
	

package demo;


import java.util.Scanner;

enum Menu{

	SAMOSA(15),IDLI(30),DOSA(50),TEA(10),COFFEE(15);
	
	private double value;
	
	Menu(int value)
	{
		this.value=value;
	}
	
	public double getValue() {
		return value;
	}
	
}
public class FoodMenu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double billAmount=0.0;
		int quantity=0,ch=0;
		
		System.out.println("----------Welecome------------");
		Menu choice=null;
		do {
			System.out.println("----------MENU------------");
			for(int i=1;i<Menu.values().length;i++)
			{
				System.out.println((i)+". "+Menu.valueOf(Menu.values()[i].toString())+"  "+Menu.values()[i].getValue());
			}
			System.out.println("6. Generate Bill");
			System.out.println("Enter your choice ::");
			if((ch=sc.nextInt())!=6)
				choice=Menu.values()[ch];
			else
				break;
			switch(choice) {
			case SAMOSA:
				System.out.println("Enter quantity :");
				quantity=sc.nextInt();
				billAmount=billAmount+ Menu.valueOf(choice.toString()).getValue()*quantity;
				break;
			case IDLI:
				System.out.println("Enter quantity :");
				quantity=sc.nextInt();
				billAmount=billAmount+ Menu.valueOf(choice.toString()).getValue()*quantity;
				break;
			case DOSA:
				System.out.println("Enter quantity :");
				quantity=sc.nextInt();
				billAmount=billAmount+ Menu.valueOf(choice.toString()).getValue()*quantity;
				break;
			case TEA:
				System.out.println("Enter quantity :");
				quantity=sc.nextInt();
				billAmount=billAmount+ Menu.valueOf(choice.toString()).getValue()*quantity;
				break;
			case COFFEE:
				System.out.println("Enter quantity :");
				quantity=sc.nextInt();
				billAmount=billAmount+ Menu.valueOf(choice.toString()).getValue()*quantity;
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
			
		} while(choice.ordinal()!=6);
		System.out.println("Bill Amount::"+billAmount);

	}

}

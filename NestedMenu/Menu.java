package com.sunbeam.tester;

import java.util.Scanner;

public class Menu {
	public static int mainMenu(Scanner sc) {
		System.out.println("-----Part Menu--------");
		System.out.println("0. Exit");
		System.out.println("1. Customer");
		System.out.println("2. Vehicle");
		System.out.println("3. Service Request");
		System.out.println("4. Parts");
		System.out.println("5. Today’s Business");
		System.out.println("6. Given Date’s Business");
		System.out.println("Enter your choice::");
		return sc.nextInt();
	}
	
	public static int partsMenu(Scanner sc) {
		System.out.println("-----Part Menu--------");
		System.out.println("0. Exit");
		System.out.println("1. Add Part");
		System.out.println("2. Display All Parts");
		System.out.println("3. Edit Part Price");
		System.out.println("4. Delete Part");
		System.out.println("Enter your choice::");
		return sc.nextInt();
	}
	
	public static int vehicleMenu(Scanner sc) {
		System.out.println("-----Vehicle Menu--------");
		System.out.println("0. Exit");
		System.out.println("1. Add Vehicle");
		System.out.println("2. Display All Vehicles");
		System.out.println("3. Display Customer Vehicles");
		System.out.println("4. Edit Vehicle");
		System.out.println("5. Delete Vehicle");
		System.out.println("Enter your choice::");
		return sc.nextInt();
	}
	
	public static int customerMenu(Scanner sc) {
		System.out.println("-----Customer Menu--------");
		System.out.println("0. Exit");
		System.out.println("1. Add customer");
		System.out.println("2. display all customers");
		System.out.println("3. display specific customer");
		System.out.println("4. Edit Customer");
		System.out.println("5. delete customer");
		System.out.println("Enter your choice::");
		return sc.nextInt();
	}
	
	public static int subMenuServiceRequest(Scanner sc) {
		
		System.out.println("----------------------------------------------------------");
		System.out.println("0. Go Back");
		System.out.println("1. Select Customer Vehical");
		System.out.println("2. Process Request");
		System.out.println("3. Prepare and Display Bill");
		System.out.println("4. Get bill payment from customer");
		System.out.println("Enter Your Choice from above List");
		int choice = sc.nextInt();
		return choice;
	}

	public static int subMenuProcessRequest(Scanner sc) {

		System.out.println("----------------------------------------------------------");
		System.out.println("0. Go Back");
		System.out.println("1. New Service");
		System.out.println("2. Existing Service");
		System.out.println("3. Maintainance");
		System.out.println("4. Reparing");
		System.out.println("5. Engine Oil Change / Additives");
		System.out.println("Enter Your Choice from above List");
		int choice = sc.nextInt();
		return choice;
	}
}

package sunbeam.servicestation;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	// Date Functions
	public static Date getToday() {
		return getDate(Calendar.getInstance());
	}
	public static Date getDate(Calendar temp) {
		Calendar date = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH), temp.get(Calendar.DAY_OF_MONTH));
		return date.getTime();
	}
	public static Date getDate(int day, int month, int year) {
		Calendar temp = new GregorianCalendar(year, month-1, day);
		return getDate(temp);
	}
	public static Date getDate(String dateStr) {
		String[] dateParts = dateStr.split("-");
		int day = Integer.parseInt(dateParts[0]);
		int month = Integer.parseInt(dateParts[1]);
		int year = Integer.parseInt(dateParts[2]);
		return getDate(day, month, year);
	}
	// Customer & Vehicle Functions
	public static void inputCustomer(Customer cust) {
		System.out.println("Enter Customer Details : ");
		System.out.print("Name : ");
		cust.setName(sc.next());
		System.out.print("Address : ");
		cust.setAddress(sc.next());
		System.out.print("Mobile : ");
		cust.setMobile(sc.next());
	}
	public static void inputVehicle(Vehicle veh) {
		System.out.println("Enter Vehicle Details : ");
		System.out.print("Number : ");
		veh.setNumber(sc.next());
		System.out.print("Company : ");
		veh.setCompany(sc.next());
		System.out.print("Model : ");
		veh.setModel(sc.next());
	}
	public static void displayCustomerList() {
		for (Customer cust : ServiceStation.station.getCustList()) {
			System.out.println(cust);
		}
	}
	public static void displayVehicleList(Customer cust) {
		for (Vehicle veh : cust.getVehicleList()) {
			System.out.println(veh);
		}
	}
	// Service Functions
	public static void inputOilService(OilService service) {
		System.out.print("Enter Oil/Additive Name : ");
		service.setDesc(sc.next());
		System.out.print("Enter Oil/Additive Cost : ");
		service.setCost(sc.nextDouble());
	}

	public static void inputMaintenenceService(MaintenanceService service) {
		System.out.print("Enter Maintence Name : ");
		service.setDesc(sc.next());
		System.out.print("Enter Maintence Labor Charges : ");
		service.setLaborCharges(sc.nextDouble());
		inputMaintenanceParts(service);
	}
	
	public static void inputMaintenanceParts(MaintenanceService service) {
		int choice;
		do {
			System.out.print("0. Maintenance Done\n1. Add new part\nEnter choice : ");
			choice = sc.nextInt();
			if(choice==1) {
				SparePart part = new SparePart();
				System.out.print("Input Part Desc : ");
				part.setDesc(sc.next());
				System.out.print("Input Part Price : ");
				part.setRate(sc.nextDouble());
				service.newSparePart(part);
			}
		}while(choice!=0);
	}
	
	public static void main(String[] args) {
		int choice, option;
		double cash, amount;
		Customer cust;
		Vehicle veh;
		String temp;
		ServiceRequest req;
		Service service;
		Bill bill;
		System.out.println(welcome());
		System.out.printf("\n\nEnjoy Programming.\n\n");
		System.out.printf("\nPress enter to continue.\n");
		sc.nextLine();

		ServiceStation.station.loadCustDetails();
		System.out.println("Number of Customers Loaded : " + ServiceStation.station.getCustList().size());
		Main.displayCustomerList();
		ServiceStation.station.loadBillDetails();
		System.out.println("Number of Bills Loaded : " + ServiceStation.station.getBillList().size());
		do {
			System.out.print("\n\n0. Exit\n1. New Customer\n2. New Service Request\n3. Today's Business\n4. Given Date Business\nEnter Choice.. ");
			choice = sc.nextInt();
			switch (choice) {
			case 1: // New Customer
				cust = new Customer();
				Main.inputCustomer(cust);
				ServiceStation.station.newCustomer(cust);
				break;
			case 2: // New Request
				System.out.print("Enter Customer Name : ");
				temp = sc.next();
				cust = ServiceStation.station.findCustomer(temp);
				if(temp==null) {
					System.out.println("Customer Not Found");
					break;
				}
				
				Main.displayVehicleList(cust);
				veh = null;
				do{
					System.out.print("Enter Vehicle Number or 0 for New Vehicle : ");
					temp = sc.next();
					if(temp.trim().equals("0")) {
						veh = new Vehicle();
						inputVehicle(veh);
						cust.newVehicle(veh);
					}
					else {
						veh = cust.getVehicle(temp);
					}
				}while(veh==null);
				
				req = ServiceStation.station.newRequest(cust.getName(), veh.getNumber());
				do {
					System.out.print("\n0. Servicing Done\n1. Oil Servicing\n2. Maintenance Servicing\nEnter Choice : ");
					option = sc.nextInt();
					switch(option) {
					case 1: // Oil
						service = new OilService();
						Main.inputOilService((OilService) service);
						req.newService(service);
						break;
					case 2: // Maintenance
						service = new MaintenanceService();
						Main.inputMaintenenceService((MaintenanceService) service);
						req.newService(service);
						break;
					}
				}while(option!=0);
				
				bill = ServiceStation.station.newBill(req);
				bill.computeAmount();
				bill.print(System.out);
				
				System.out.print("Enter Amount Paid by Cust : ");
				amount = sc.nextDouble();
				amount = cust.payBill(bill, amount);
				System.out.println("Payment Received.");
				if(amount > 0)
					System.out.printf("Balance Amount : %.2f\n", amount);
				break;
			case 3: // Today's Business
				cash = ServiceStation.station.computeCash(Main.getToday());
				System.out.println("Today's Business : " + cash);
				break;
			case 4: // Given Date Business
				try {
					System.out.print("Enter Date (dd-MM-yyyy) : ");
					temp = sc.next();
					cash = ServiceStation.station.computeCash(Main.getDate(temp));
					System.out.println(temp + " Business : " + cash);
				} catch (Exception e) {
					System.out.println("Date entered is Invalid.");
				}
				break;
			}
		}while(choice!=0);
		sc.close();
		ServiceStation.station.storeBillDetails();
		ServiceStation.station.storeCustDetails();
	}
	public static String welcome() {
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBytes);
		out.printf("Java My Project @ SunBeam Infotech\n\n");
		out.printf("Vehicle Service Station\n\n");
		out.printf("This is simplified version of the case study implementation.\n");
		out.printf("Consider this as base and implement case study of your own.\n");
		out.printf("This implementation should help you to understand requirements better. First read the given documentation of case study.\n\n");
		out.printf("However most of the validations are not implemented. So be careful while trying it.\n");
		out.printf("Note that all string inputs are considered without white space.\n");
		out.printf("Given a dummy customer database as sample and is used in this program.\n");
		out.flush();
		String text = outBytes.toString();
		out.close();
		return text;
	}
}

package com.sunbeam.tester;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import com.sunbeam.entity.Bill;
import com.sunbeam.entity.Customer;
import com.sunbeam.entity.MaintenanceService;
import com.sunbeam.entity.OilService;
import com.sunbeam.entity.Service;
import com.sunbeam.entity.ServiceRequest;

import com.sunbeam.entity.SparePart;
import com.sunbeam.entity.Vehicle;
import com.sunbeam.entity.VehicleModel;
import com.sunbeam.service.CustomerService;
import com.sunbeam.service.PartService;
import com.sunbeam.service.ServiceRequestService;
import com.sunbeam.service.ServiceService;
import com.sunbeam.service.ServiceStation;

public class Main {
	private static Scanner sc = new Scanner(System.in);

	// Date Functions
	


	public static void inputVehicle(Vehicle veh) {
		
		
	}

	public static void displayVehicleList(Customer cust) {
		
	}

	// Service Functions
	public static void inputOilService(OilService service) {
		
	}

	public static void inputMaintenenceService(MaintenanceService service) {
		
	}

	public static void inputMaintenanceParts(MaintenanceService service) {
		
	}

	public static void displayAllVehicles() {
		
	}

	// SERVICE REQUEST SUB MENU
	public static void subMenuServiceRequestOptions() {
		int choice;
		Customer cust = null;
		Vehicle veh = null;
		String temp=null;
		ServiceRequest req = null;
		Bill bill = null;
		double cash, amount;
		
		while ((choice = Menu.subMenuServiceRequest(sc)) != 0)
			switch (choice) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			}
	}

	// PROCESS REQUEST SUB MENU
	public static void subMenuProcessRequestOptions(Customer cust, Vehicle veh) {
		int choice;
		ServiceRequest serviceRequest = null;
		Service service = null;
		while ((choice = Menu.subMenuProcessRequest(sc)) != 0)
			switch (choice) {
			case 1:
				
				
				break;
			case 2:
				serviceRequest=ServiceRequestService.selectExistingServiceRequest(sc);
				break;
			case 3:
				if (serviceRequest != null)
					ServiceService.doMaintainance(serviceRequest,sc);
				else
					System.out.println("Select the Service...");
				break;
			case 4:
				if (serviceRequest != null)
					ServiceService.doRepair(serviceRequest,sc);
				else
					System.out.println("Select the Service...");
				break;
			case 5:
				if (serviceRequest != null) {
					service = new OilService();
					Main.inputOilService((OilService) service);
					serviceRequest.newService(service);
				} else
					System.out.println("Select the Service...");
				break;
			}

	}

	public static void vehicleFunctionality() {
		int choice;
		while ((choice = Menu.vehicleMenu(sc)) != 0) {
			switch (choice) {

			case 1:
				Main.inputVehicle(new Vehicle());
				break;
			case 2:
				displayAllVehicles();
				break;
			case 3:
				CustomerService.displayCustomerVehicles(sc);
				break;
			case 4:
				break;
			case 5:
				break;
			default:
				System.out.println("Invalid choice!!");
				break;
			}

		}
	}

	public static void partsFunctionality() {
		int choice;
		SparePart part = null;
		while ((choice = Menu.partsMenu(sc)) != 0) {
			switch (choice) {

			case 1:
				part = new SparePart();
				PartService.addPart(part, sc);
				ServiceStation.station.newPart(part);
				break;
			case 2:
				PartService.displayPartList();
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid choice!!");
				break;
			}

		}
	}

	public static void customerFunctionality() {
		int choice;
		Customer cust;
		while ((choice = Menu.customerMenu(sc)) != 0) {
			switch (choice) {
			case 1:
				cust = new Customer();
				CustomerService.inputCustomer(cust, sc);
				ServiceStation.station.newCustomer(cust);
				break;
			case 2:
				CustomerService.displayCustomerList();
				break;
			case 3:
				System.out.println("Enter mobile Number::");
				String mobile = sc.next();
				CustomerService.displaySpecificCustomer(mobile);
				break;
			case 4:
				break;
			case 5:
				break;
			default:
				break;
			}
		}
	}

	public static void main(String[] args) {
		int choice;
		double cash, amount;
		
		String temp;
		Bill bill;
		System.out.println(welcome());
		System.out.printf("\n\nEnjoy Programming.\n\n");
		System.out.printf("\nPress enter to continue.\n");
		sc.nextLine();

		// load customer details
		ServiceStation.station.loadCustDetails();
		System.out.println("Number of Customers Loaded : " + ServiceStation.station.getCustList().size());
		CustomerService.displayCustomerList();

		// load bill details
		ServiceStation.station.loadBillDetails();
		System.out.println("Number of Bills Loaded : " + ServiceStation.station.getBillList().size());

		// loading vehicleModel details if exists
		ServiceStation.station.loadVehicleModelDetails();
		if (ServiceStation.station.getVehicleModelsList().size() == 0)
			ServiceStation.station.persistVehicleModels();

		// loading part details if exists
		ServiceStation.station.loadPartDetails();
		if (ServiceStation.station.getPartList().size() == 0)
			ServiceStation.station.persistParts();
		
		// loading service request details if exists
		ServiceStation.station.loadServiceRequestsDetails();
		System.out.println("Service Request details Loaded : " + ServiceStation.station.getServiceRequestList().size());
		

		while ((choice = Menu.mainMenu(sc)) != 0) {
			switch (choice) {
			case 1: // Customer functionality
				Main.customerFunctionality();
				break;
			case 2: // vehicle functionality
				Main.vehicleFunctionality();
				break;
			case 3: // service Request
				Main.subMenuServiceRequestOptions();
				break;
			case 4: // Part functionality
				Main.partsFunctionality();
				break;
			case 5: // Today's Business
				
				break;
			case 6:// Given Date Business
				
				break;
			default:
				System.out.println("Invalid coice!!");
				break;
			}
		}
		sc.close();
		ServiceStation.station.storeBillDetails();
		ServiceStation.station.storeCustDetails();
		ServiceStation.station.storePartDetails();
		ServiceStation.station.storeVehicleModelDetails();
		ServiceStation.station.storeServiceRequestsDetails();
	}

	public static String welcome() {
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBytes);
		out.printf("Java My Project @ SunBeam Infotech\n\n");
		out.printf("Vehicle Service Station\n\n");
		out.printf("This is simplified version of the case study implementation.\n");
		out.printf("Consider this as base and implement case study of your own.\n");
		out.printf(
				"This implementation should help you to understand requirements better. First read the given documentation of case study.\n\n");
		out.printf("However most of the validations are not implemented. So be careful while trying it.\n");
		out.printf("Note that all string inputs are considered without white space.\n");
		out.printf("Given a dummy customer database as sample and is used in this program.\n");
		out.flush();
		String text = outBytes.toString();
		out.close();
		return text;
	}
}

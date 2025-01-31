package p5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

// Backend Classes
class Customer implements Serializable {
	private String name, address, mobileNumber;
	private Map<String, Vehicle> vehicles = new HashMap<>();

	public Customer(String name, String address, String mobileNumber) {
		this.name = name;
		this.address = address;
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public Map<String, Vehicle> getVehicles() {
		return vehicles;
	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.put(vehicle.getVehicleNumber(), vehicle);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Customer customer = (Customer) o;
		return mobileNumber.equals(customer.mobileNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mobileNumber);
	}

	@Override
	public String toString() {
		return "Customer{" + "name='" + name + '\'' + ", address='" + address + '\'' + ", mobileNumber='" + mobileNumber
				+ '\'' + ", vehicles=" + vehicles + '}';
	}
}

class Vehicle implements Serializable {
	private String manufacturer, model, vehicleNumber;

	public Vehicle(String manufacturer, String model, String vehicleNumber) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.vehicleNumber = vehicleNumber;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getModel() {
		return model;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Vehicle vehicle = (Vehicle) o;
		return vehicleNumber.equals(vehicle.vehicleNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vehicleNumber);
	}

	@Override
	public String toString() {
		return "Vehicle{" + "manufacturer='" + manufacturer + '\'' + ", model='" + model + '\'' + ", vehicleNumber='"
				+ vehicleNumber + '\'' + '}';
	}
}

class ServiceStation implements Serializable {
	private Set<Customer> customers = new HashSet<>();
	public LinkedList<Bill> bills = new LinkedList<>();
	private Set<Part> availableParts = new HashSet<>();

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void removeCustomer(Customer customer) {
		customers.remove(customer);
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void addBill(Bill bill) {
		bills.add(bill);
	}

	public LinkedList<Bill> getBills() {
		return bills;
	}

	public void addPart(Part part) {
		availableParts.add(part);
	}

	public Set<Part> getAvailableParts() {
		return availableParts;
	}

	public double calculateTodaysBusiness() {
		double total = 0.0;
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		String today = fmt.format(new Date());
		for (Bill bill : bills) {
			if (fmt.format(bill.getServiceDate()).equals(today)) {
				total += bill.getFinalAmount();
			}
		}
		return total;
	}

	public double calculateBusinessForDate(Date date) {
		double total = 0.0;
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		String targetDate = fmt.format(date);
		for (Bill bill : bills) {
			if (fmt.format(bill.getServiceDate()).equals(targetDate)) {
				total += bill.getFinalAmount();
			}
		}
		return total;
	}
}

class Bill implements Serializable {
	private ServiceRequest serviceRequest;
	private double finalAmount;
	private Date serviceDate;

	public Bill(ServiceRequest serviceRequest, double finalAmount, Date serviceDate) {
		this.serviceRequest = serviceRequest;
		this.finalAmount = finalAmount;
		this.serviceDate = serviceDate;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
}

class ServiceRequest implements Serializable {
	private Customer customer;
	private Vehicle vehicle;
	private LinkedList<Service> services = new LinkedList<>();
	private double billAmount;

	public ServiceRequest(Customer customer, Vehicle vehicle) {
		this.customer = customer;
		this.vehicle = vehicle;
	}

	public void addService(Service service) {
		services.add(service);
		billAmount += service.calculateCost();
	}

	public double getBillAmount() {
		return billAmount;
	}

	public LinkedList<Service> getServices() {
		return services;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
}

class Service implements Serializable {
	private String serviceType;
	private LinkedList<Part> partsReplaced = new LinkedList<>();
	private double laborCharges;

	public Service(String serviceType, double laborCharges) {
		this.serviceType = serviceType;
		this.laborCharges = laborCharges;
	}

	public void addPart(Part part) {
		partsReplaced.add(part);
	}

	public double calculateCost() {
		double partsCost = partsReplaced.stream().mapToDouble(Part::getPrice).sum();
		return partsCost + laborCharges;
	}

	// Add getter methods for serviceType and laborCharges
	public String getServiceType() {
		return serviceType;
	}

	public double getLaborCharges() {
		return laborCharges;
	}

	public LinkedList<Part> getPartsReplaced() {
		return partsReplaced;
	}
}

class Part implements Serializable {
	private String partId, partName;
	private double price;

	public Part(String partId, String partName, double price) {
		this.partId = partId;
		this.partName = partName;
		this.price = price;
	}

	public String getPartId() {
		return partId;
	}

	public String getPartName() {
		return partName;
	}

	public double getPrice() {
		return price;
	}
}

// GUI Implementation
public class ServiceStationGUI extends JFrame {
	private ServiceStation serviceStation = new ServiceStation();

	public ServiceStationGUI() {
		setTitle("Vehicle Service Station");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Main Menu
		JPanel mainPanel = new JPanel(new GridLayout(6, 1, 10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JButton customerButton = new JButton("Customer Management");
		JButton vehicleButton = new JButton("Vehicle Management");
		JButton serviceButton = new JButton("Service Request");
		JButton partsButton = new JButton("Parts Management");
		JButton businessButton = new JButton("Business Details");
		JButton exitButton = new JButton("Exit");

		// Set button styles
		Font buttonFont = new Font("Arial", Font.BOLD, 16);
		customerButton.setFont(buttonFont);
		vehicleButton.setFont(buttonFont);
		serviceButton.setFont(buttonFont);
		partsButton.setFont(buttonFont);
		businessButton.setFont(buttonFont);
		exitButton.setFont(buttonFont);

		mainPanel.add(customerButton);
		mainPanel.add(vehicleButton);
		mainPanel.add(serviceButton);
		mainPanel.add(partsButton);
		mainPanel.add(businessButton);
		mainPanel.add(exitButton);

		add(mainPanel);

		// Button Actions
		customerButton.addActionListener(e -> openCustomerManagement());
		vehicleButton.addActionListener(e -> openVehicleManagement());
		serviceButton.addActionListener(e -> createServiceRequest());
		partsButton.addActionListener(e -> openPartsManagement());
		businessButton.addActionListener(e -> displayTodaysBusiness());
		exitButton.addActionListener(e -> System.exit(0));
	}

	private void openCustomerManagement() {
		JFrame customerFrame = new JFrame("Customer Management");
		customerFrame.setSize(500, 400);
		customerFrame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JButton addCustomerButton = new JButton("Add Customer");
		JButton editCustomerButton = new JButton("Edit Customer");
		JButton deleteCustomerButton = new JButton("Delete Customer");
		JButton displayAllCustomersButton = new JButton("Display All Customers");
		JButton backButton = new JButton("Back");

		panel.add(addCustomerButton);
		panel.add(editCustomerButton);
		panel.add(deleteCustomerButton);
		panel.add(displayAllCustomersButton);
		panel.add(backButton);

		customerFrame.add(panel);
		customerFrame.setVisible(true);

		// Button Actions
		addCustomerButton.addActionListener(e -> addCustomer());
		editCustomerButton.addActionListener(e -> editCustomer());
		deleteCustomerButton.addActionListener(e -> deleteCustomer());
		displayAllCustomersButton.addActionListener(e -> displayAllCustomers());
		backButton.addActionListener(e -> customerFrame.dispose());
	}

	private void addCustomer() {
		JFrame addCustomerFrame = new JFrame("Add Customer");
		addCustomerFrame.setSize(400, 300);
		addCustomerFrame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JTextField nameField = new JTextField();
		JTextField addressField = new JTextField();
		JTextField mobileField = new JTextField();
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");

		panel.add(new JLabel("Name:"));
		panel.add(nameField);
		panel.add(new JLabel("Address:"));
		panel.add(addressField);
		panel.add(new JLabel("Mobile Number:"));
		panel.add(mobileField);
		panel.add(saveButton);
		panel.add(cancelButton);

		addCustomerFrame.add(panel);
		addCustomerFrame.setVisible(true);

		// Save Button Action
		saveButton.addActionListener(e -> {
			String name = nameField.getText();
			String address = addressField.getText();
			String mobileNumber = mobileField.getText();

			if (name.isEmpty() || address.isEmpty() || mobileNumber.isEmpty()) {
				JOptionPane.showMessageDialog(addCustomerFrame, "All fields are required!");
				return;
			}

			Customer customer = new Customer(name, address, mobileNumber);
			serviceStation.addCustomer(customer);
			JOptionPane.showMessageDialog(addCustomerFrame, "Customer Added Successfully!");
			addCustomerFrame.dispose();
		});

		// Cancel Button Action
		cancelButton.addActionListener(e -> addCustomerFrame.dispose());
	}

	private void editCustomer() {
		JFrame editCustomerFrame = new JFrame("Edit Customer");
		editCustomerFrame.setSize(400, 300);
		editCustomerFrame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JTextField mobileField = new JTextField();
		JTextField nameField = new JTextField();
		JTextField addressField = new JTextField();
		JButton searchButton = new JButton("Search");
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");

		panel.add(new JLabel("Mobile Number:"));
		panel.add(mobileField);
		panel.add(new JLabel("Name:"));
		panel.add(nameField);
		panel.add(new JLabel("Address:"));
		panel.add(addressField);
		panel.add(searchButton);
		panel.add(saveButton);
		panel.add(cancelButton);

		editCustomerFrame.add(panel);
		editCustomerFrame.setVisible(true);

		// Search Button Action
		searchButton.addActionListener(e -> {
			String mobileNumber = mobileField.getText();
			Customer customer = findCustomerByMobileNumber(mobileNumber);
			if (customer != null) {
				mobileField.setEditable(false);
				nameField.setText(customer.getName());
				addressField.setText(customer.getAddress());
			} else {
				JOptionPane.showMessageDialog(editCustomerFrame, "Customer not found!");
			}
		});

		// Save Button Action
		saveButton.addActionListener(e -> {
			String mobileNumber = mobileField.getText();
			String name = nameField.getText();
			String address = addressField.getText();

			Customer customer = findCustomerByMobileNumber(mobileNumber);
			if (customer != null) {
				customer = new Customer(name, address, mobileNumber);
				serviceStation.removeCustomer(customer);
				serviceStation.addCustomer(customer);
				JOptionPane.showMessageDialog(editCustomerFrame, "Customer Updated Successfully!");
				editCustomerFrame.dispose();
			} else {
				JOptionPane.showMessageDialog(editCustomerFrame, "Customer not found!");
			}
		});

		// Cancel Button Action
		cancelButton.addActionListener(e -> editCustomerFrame.dispose());
	}

	private void deleteCustomer() {
		JFrame deleteCustomerFrame = new JFrame("Delete Customer");
		deleteCustomerFrame.setSize(400, 200);
		deleteCustomerFrame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JTextField mobileField = new JTextField();
		JButton deleteButton = new JButton("Delete");
		JButton cancelButton = new JButton("Cancel");

		panel.add(new JLabel("Mobile Number:"));
		panel.add(mobileField);
		panel.add(deleteButton);
		panel.add(cancelButton);

		deleteCustomerFrame.add(panel);
		deleteCustomerFrame.setVisible(true);

		// Delete Button Action
		deleteButton.addActionListener(e -> {
			String mobileNumber = mobileField.getText();
			Customer customer = findCustomerByMobileNumber(mobileNumber);
			if (customer != null) {
				serviceStation.removeCustomer(customer);
				JOptionPane.showMessageDialog(deleteCustomerFrame, "Customer Deleted Successfully!");
				deleteCustomerFrame.dispose();
			} else {
				JOptionPane.showMessageDialog(deleteCustomerFrame, "Customer not found!");
			}
		});

		// Cancel Button Action
		cancelButton.addActionListener(e -> deleteCustomerFrame.dispose());
	}

	private void displayAllCustomers() {
		JFrame displayFrame = new JFrame("All Customers");
		displayFrame.setSize(500, 400);
		displayFrame.setLocationRelativeTo(null);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);

		StringBuilder sb = new StringBuilder();
		for (Customer customer : serviceStation.getCustomers()) {
			sb.append(customer.toString()).append("\n");
		}

		textArea.setText(sb.toString());
		displayFrame.add(scrollPane);
		displayFrame.setVisible(true);
	}

	private void openVehicleManagement() {
		JFrame vehicleFrame = new JFrame("Vehicle Management");
		vehicleFrame.setSize(500, 400);
		vehicleFrame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JButton addVehicleButton = new JButton("Add Vehicle");
		JButton backButton = new JButton("Back");

		addVehicleButton.addActionListener(e -> addVehicle());
		panel.add(addVehicleButton);
		panel.add(backButton);

		backButton.addActionListener(e -> vehicleFrame.dispose());
		vehicleFrame.add(panel);
		vehicleFrame.setVisible(true);
	}

	private void addVehicle() {
		JFrame addVehicleFrame = new JFrame("Add Vehicle");
		addVehicleFrame.setSize(400, 300);
		addVehicleFrame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JTextField mobileField = new JTextField();
		JTextField manufacturerField = new JTextField();
		JTextField modelField = new JTextField();
		JTextField vehicleNumberField = new JTextField();
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");

		panel.add(new JLabel("Customer Mobile Number:"));
		panel.add(mobileField);
		panel.add(new JLabel("Manufacturer:"));
		panel.add(manufacturerField);
		panel.add(new JLabel("Model:"));
		panel.add(modelField);
		panel.add(new JLabel("Vehicle Number:"));
		panel.add(vehicleNumberField);
		panel.add(saveButton);
		panel.add(cancelButton);

		addVehicleFrame.add(panel);
		addVehicleFrame.setVisible(true);

		// Save Button Action
		saveButton.addActionListener(e -> {
			String mobileNumber = mobileField.getText();
			String manufacturer = manufacturerField.getText();
			String model = modelField.getText();
			String vehicleNumber = vehicleNumberField.getText();

			if (mobileNumber.isEmpty() || manufacturer.isEmpty() || model.isEmpty() || vehicleNumber.isEmpty()) {
				JOptionPane.showMessageDialog(addVehicleFrame, "All fields are required!");
				return;
			}

			Customer customer = findCustomerByMobileNumber(mobileNumber);
			if (customer != null) {
				Vehicle vehicle = new Vehicle(manufacturer, model, vehicleNumber);
				customer.addVehicle(vehicle);
				JOptionPane.showMessageDialog(addVehicleFrame, "Vehicle Added Successfully!");
				addVehicleFrame.dispose();
			} else {
				JOptionPane.showMessageDialog(addVehicleFrame, "Customer not found!");
			}
		});

		// Cancel Button Action
		cancelButton.addActionListener(e -> addVehicleFrame.dispose());
	}

	private void openPartsManagement() {
		JFrame partsFrame = new JFrame("Parts Management");
		partsFrame.setSize(500, 400);
		partsFrame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JButton addPartButton = new JButton("Add Part");
		JButton viewPartsButton = new JButton("View Available Parts");
		JButton backButton = new JButton("Back");

		panel.add(addPartButton);
		panel.add(viewPartsButton);
		panel.add(backButton);

		partsFrame.add(panel);
		partsFrame.setVisible(true);

		// Button Actions
		addPartButton.addActionListener(e -> addPart());
		viewPartsButton.addActionListener(e -> viewAvailableParts());
		backButton.addActionListener(e -> partsFrame.dispose());
	}

	private void addPart() {
		JFrame addPartFrame = new JFrame("Add Part");
		addPartFrame.setSize(400, 300);
		addPartFrame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JTextField partIdField = new JTextField();
		JTextField partNameField = new JTextField();
		JTextField priceField = new JTextField();
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");

		panel.add(new JLabel("Part ID:"));
		panel.add(partIdField);
		panel.add(new JLabel("Part Name:"));
		panel.add(partNameField);
		panel.add(new JLabel("Price:"));
		panel.add(priceField);
		panel.add(saveButton);
		panel.add(cancelButton);

		addPartFrame.add(panel);
		addPartFrame.setVisible(true);

		// Save Button Action
		saveButton.addActionListener(e -> {
			String partId = partIdField.getText();
			String partName = partNameField.getText();
			String priceText = priceField.getText();

			if (partId.isEmpty() || partName.isEmpty() || priceText.isEmpty()) {
				JOptionPane.showMessageDialog(addPartFrame, "All fields are required!");
				return;
			}

			try {
				double price = Double.parseDouble(priceText);
				Part part = new Part(partId, partName, price);
				serviceStation.addPart(part);
				JOptionPane.showMessageDialog(addPartFrame, "Part Added Successfully!");
				addPartFrame.dispose();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(addPartFrame, "Invalid price format!");
			}
		});

		// Cancel Button Action
		cancelButton.addActionListener(e -> addPartFrame.dispose());
	}

	private void viewAvailableParts() {
		JFrame partsFrame = new JFrame("Available Parts");
		partsFrame.setSize(500, 400);
		partsFrame.setLocationRelativeTo(null);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);

		StringBuilder sb = new StringBuilder();
		for (Part part : serviceStation.getAvailableParts()) {
			sb.append("Part ID: ").append(part.getPartId()).append(", ");
			sb.append("Part Name: ").append(part.getPartName()).append(", ");
			sb.append("Price: $").append(part.getPrice()).append("\n");
		}

		textArea.setText(sb.toString());
		partsFrame.add(scrollPane);
		partsFrame.setVisible(true);
	}

	// Add a class-level variable to store the current ServiceRequest
	private ServiceRequest currentServiceRequest;
	

	private void createServiceRequest() {
		JFrame serviceRequestFrame = new JFrame("Create Service Request");
		serviceRequestFrame.setSize(600, 500);
		serviceRequestFrame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JTextField mobileField = new JTextField();
		JTextField vehicleNumberField = new JTextField();
		JTextField serviceTypeField = new JTextField();
		JTextField laborChargesField = new JTextField();
		JButton addServiceButton = new JButton("Add Service");
		JButton generateBillButton = new JButton("Generate Bill");
		JButton cancelButton = new JButton("Cancel");

		panel.add(new JLabel("Customer Mobile Number:"));
		panel.add(mobileField);
		panel.add(new JLabel("Vehicle Number:"));
		panel.add(vehicleNumberField);
		panel.add(new JLabel("Service Type:"));
		panel.add(serviceTypeField);
		panel.add(new JLabel("Labor Charges:"));
		panel.add(laborChargesField);
		panel.add(addServiceButton);
		panel.add(generateBillButton);
		panel.add(cancelButton);

		serviceRequestFrame.add(panel);
		serviceRequestFrame.setVisible(true);

		// Add Service Button Action
		addServiceButton.addActionListener(e -> {
			String mobileNumber = mobileField.getText();
			String vehicleNumber = vehicleNumberField.getText();
			String serviceType = serviceTypeField.getText();
			String laborChargesText = laborChargesField.getText();

			try {
				double laborCharges = Double.parseDouble(laborChargesText);
				Customer customer = findCustomerByMobileNumber(mobileNumber);
				if (customer != null) {
					Vehicle vehicle = customer.getVehicles().get(vehicleNumber);
					if (vehicle != null) {
						if (currentServiceRequest == null) {
							currentServiceRequest = new ServiceRequest(customer, vehicle);
						}
						Service service = new Service(serviceType, laborCharges);
						currentServiceRequest.addService(service);
						JOptionPane.showMessageDialog(serviceRequestFrame, "Service Added Successfully!");
					} else {
						JOptionPane.showMessageDialog(serviceRequestFrame, "Vehicle not found!");
					}
				} else {
					JOptionPane.showMessageDialog(serviceRequestFrame, "Customer not found!");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(serviceRequestFrame, "Invalid labor charges format!");
			}
		});

		// Generate Bill Button Action
		generateBillButton.addActionListener(e -> {
			if (currentServiceRequest != null) {
				double finalAmount = currentServiceRequest.getBillAmount();
				Bill bill = new Bill(currentServiceRequest, finalAmount, new Date());
				serviceStation.addBill(bill);
				JOptionPane.showMessageDialog(serviceRequestFrame,
						"Bill Generated Successfully! Amount: $" + finalAmount);
				saveBillToFile(bill); // Save the bill to a file
				currentServiceRequest = null; // Reset the current service request
				serviceRequestFrame.dispose();
			} else {
				JOptionPane.showMessageDialog(serviceRequestFrame, "No services added to generate a bill!");
			}
		});

		// Cancel Button Action
		cancelButton.addActionListener(e -> serviceRequestFrame.dispose());
	}

	private void displayTodaysBusiness() {
		double totalBusiness = serviceStation.calculateTodaysBusiness();
		JOptionPane.showMessageDialog(this, "Today's Total Business: $" + totalBusiness);
	}

	private Customer findCustomerByMobileNumber(String mobileNumber) {
		for (Customer customer : serviceStation.getCustomers()) {
			if (customer.getMobileNumber().equals(mobileNumber)) {
				return customer;
			}
		}
		return null;
	}

	private void saveBillToFile(Bill bill) {
		String fileName = "Bill_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write("=== Bill Details ===\n");
			writer.write("Customer: " + bill.getServiceRequest().getCustomer().getName() + "\n");
			writer.write("Vehicle: " + bill.getServiceRequest().getVehicle().getVehicleNumber() + "\n");
			writer.write("Service Date: " + new SimpleDateFormat("dd-MM-yyyy").format(bill.getServiceDate()) + "\n");
			writer.write("Services:\n");

			for (Service service : bill.getServiceRequest().getServices()) {
				writer.write("  - Service Type: " + service.getServiceType() + "\n");
				writer.write("    Labor Charges: $" + service.getLaborCharges() + "\n");
				writer.write("    Parts Replaced:\n");
				for (Part part : service.getPartsReplaced()) {
					writer.write("      - " + part.getPartName() + " ($" + part.getPrice() + ")\n");
				}
			}

			writer.write("Total Amount: $" + bill.getFinalAmount() + "\n");
			writer.write("===================\n");
			JOptionPane.showMessageDialog(this, "Bill saved to " + fileName);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error saving bill to file: " + e.getMessage());
		}
	}

//**************/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ServiceStationGUI gui = new ServiceStationGUI();
			gui.setVisible(true);
		});
	}
}

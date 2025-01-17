package sunbeam.servicestation;

import java.io.Serializable;
import java.util.LinkedList;

public class ServiceRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String customerName;
	private String vehicleNumber;	
	private LinkedList<Service> serviceList;
	public ServiceRequest() {
		this("", "");
	}
	public ServiceRequest(String customerName, String vehicleNumber) {
		this.customerName = customerName;
		this.vehicleNumber = vehicleNumber;
		this.serviceList = new LinkedList<>();
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public LinkedList<Service> getServiceList() {
		return serviceList;
	}
	public void setServiceList(LinkedList<Service> serviceList) {
		this.serviceList = serviceList;
	}
	@Override
	public String toString() {
		return customerName + " - " + vehicleNumber;
	}
	public void newService(Service serv) {
		this.serviceList.add(serv);
	}
}

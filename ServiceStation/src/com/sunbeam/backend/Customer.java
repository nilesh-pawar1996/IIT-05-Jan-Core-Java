package sunbeam.servicestation;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String mobile;
	private double lastBalance;
	private HashMap<String, Vehicle> vehList;
	
	public Customer() {
		this("", "", "");
	}
	
	public Customer(String name, String address, String mobile) {
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.vehList = new HashMap<>();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public double getLastBalance() {
		return lastBalance;
	}

	public void setLastBalance(double lastBalance) {
		this.lastBalance = lastBalance;
	}

	public Collection<Vehicle> getVehicleList() {
		return vehList.values();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", mobile="
				+ mobile + "]";
	}
	
	public void newVehicle(Vehicle veh) {
		this.vehList.put(veh.getNumber(), veh);
	}
	
	public Vehicle getVehicle(String vehNumber) {
		return this.vehList.get(vehNumber);
	}
	
	public double payBill(Bill bill, double amount) {
		double totalBill = bill.computeTotalBill();
		double balance = totalBill - amount;
		if(balance > 0.0) {
			this.lastBalance = balance;
			bill.setPaidAmount(amount);
		} else {
			this.lastBalance = 0.0;
			bill.setPaidAmount(totalBill);
		}
		return balance;
	}
}

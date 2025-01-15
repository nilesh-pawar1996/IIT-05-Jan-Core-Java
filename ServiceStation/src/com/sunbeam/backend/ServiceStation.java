package sunbeam.servicestation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class ServiceStation {
	public static ServiceStation station = new ServiceStation();
	public static final String name = "Vehicle Service Station";
	public static final String custFilePath = "customer.sun";
	public static final String billFilePath = "billing.sun";
	private LinkedList<Bill> billList;
	private HashSet<Customer> custList;
	public ServiceStation() {
		billList = new LinkedList<>();
		custList = new HashSet<>();
	}
	public LinkedList<Bill> getBillList() {
		return billList;
	}
	public HashSet<Customer> getCustList() {
		return custList;
	}
	public void newCustomer(Customer cust) {
		this.custList.add(cust);
	}
	public Customer findCustomer(String name) {
		for (Customer cust : this.custList) {
			if(name.equalsIgnoreCase(cust.getName()))
				return cust;
		}
		return null;
	}
	public List<Bill> getBillList(Date date) {
		List<Bill> list = new ArrayList<Bill>();
		for (Bill bill : billList) {
			if(date.equals(bill.getBillDate()))
				list.add(bill);
		}
		return list;
	}
	public double computeCash(Date date) {
		double total = 0.0;
		for (Bill bill : billList) {
			if(date.equals(bill.getBillDate()))
				total += bill.getPaidAmount();
		}
		return total;
	}
	public ServiceRequest newRequest(String customer, String vehNumber) {
		ServiceRequest request = new ServiceRequest(customer, vehNumber);
		return request;
	}
	public Bill newBill(ServiceRequest req) {
		Bill bill = new Bill(req, Main.getToday());
		this.newBill(bill);
		return bill;
	}
	public void storeCustDetails() {
		FileOutputStream fout = null;
		ObjectOutputStream oout = null;
		try {
			fout = new FileOutputStream(custFilePath);
			oout = new ObjectOutputStream(fout);
			oout.writeObject(custList);
			oout.flush();
		} catch (Exception e) {
			throw new DataIOException("Customer Store Failed", e);
		} finally {
			try {
				if (oout != null)
					oout.close();
				if (fout != null)
					fout.close();
			} catch (Exception e2) {
			}
		}
	}
	public void loadCustDetails() {
		FileInputStream fin = null;
		ObjectInputStream oin = null;
		try {
			fin = new FileInputStream(custFilePath);
			oin = new ObjectInputStream(fin);
			custList = (HashSet<Customer>) oin.readObject();
		} catch(FileNotFoundException e) {
			System.out.println("Customer File Not Found.");
			System.out.println("Customer List will be Empty.");
			return;
		} catch (Exception e) {
			throw new DataIOException("Customer Load Failed", e);
		} finally {
			try {
				if (oin != null)
					oin.close();
				if (fin != null)
					fin.close();
			} catch (Exception e2) {
			}
		}
	}
	public void storeBillDetails() {
		FileOutputStream fout = null;
		ObjectOutputStream oout = null;
		try {
			fout = new FileOutputStream(billFilePath);
			oout = new ObjectOutputStream(fout);
			oout.writeObject(billList);
			oout.flush();
		} catch (Exception e) {
			throw new DataIOException("Billing Store Failed", e);
		} finally {
			try {
				if (oout != null)
					oout.close();
				if (fout != null)
					fout.close();
			} catch (Exception e2) {
			}
		}
	}
	public void loadBillDetails() {
		FileInputStream fin = null;
		ObjectInputStream oin = null;
		try {
			fin = new FileInputStream(billFilePath);
			oin = new ObjectInputStream(fin);
			billList = (LinkedList<Bill>) oin.readObject();
		} catch(FileNotFoundException e) {
			System.out.println("Bill File Not Found.");
			System.out.println("Bill List will be Empty.");
			return;
		} catch (Exception e) {
			throw new DataIOException("Billing Load Failed", e);
		} finally {
			try {
				if (oin != null)
					oin.close();
				if (fin != null)
					fin.close();
			} catch (Exception e2) {
			}
		}
	}
	public void newBill(Bill bill) {
		this.billList.add(bill);
	}
}


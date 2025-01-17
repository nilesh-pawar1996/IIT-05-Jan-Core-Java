package sunbeam.servicestation;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final double taxPercent = 12.6;
	private double lastBalance;
	private double amount;
	private double paidAmount;
	private ServiceRequest request;
	private Date billDate;
	public Bill(ServiceRequest request, Date billDate) {
		Customer cust = ServiceStation.station.findCustomer(request.getCustomerName());
		this.lastBalance = cust.getLastBalance();
		this.request = request;
		this.billDate = billDate;
	}
	
	public ServiceRequest getServiceRequest() {
		return request;
	}

	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Date getBillDate() {
		return billDate;
	}
	public double computeAmount() {
		amount = 0.0;
		for (Service serv : request.getServiceList()) {
			amount += serv.price();
		}
		return amount;
	}
	public double computeTax() {
		return this.amount * taxPercent / 100;
	}
	public double computeTotalBill() {
		double tax = computeTax();
		return this.amount + tax + this.lastBalance;
	}
	public void print(OutputStream out) {
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		PrintStream prn = new PrintStream(out);
		prn.printf("    * + * + * + %s + * + * + *\r\n", ServiceStation.name);
		prn.printf("Customer : %s\r\n", request.getCustomerName());
		prn.printf("Vehicle : %s\r\n", request.getVehicleNumber());
		prn.printf("Date : %s\r\n", fmt.format(billDate));
		prn.printf("* + * + * + * + * + * + * + * + * + * + * + * + * + * + \r\n");
		for (Service serv : request.getServiceList()) {
			prn.println(serv.getDesc() + " - Rs. " + serv.price());
			if(serv instanceof MaintenanceService) {
				prn.printf("  Labor Charges : %.2f\r\n", ((MaintenanceService)serv).getLaborCharges());
				for (SparePart part : ((MaintenanceService)serv).getPartList()) {
					prn.println("    - " + part);
				}
			}
		}
		prn.printf("* + * + * + * + * + * + * + * + * + * + * + * + * + * + \r\n");
		prn.printf("Bill Charges : %.2f\r\n", this.amount);
		prn.printf("Tax : %.2f\r\n", this.computeTax());
		if(lastBalance > 0.0)
			prn.printf("Last Balance : %.2f\r\n", lastBalance);
		prn.printf("Total Bill : %.2f\r\n", this.computeTotalBill());
		prn.printf("* + * + * + * + * + * + * + * + * + * + * + * + * + * + \r\n");
	}
}

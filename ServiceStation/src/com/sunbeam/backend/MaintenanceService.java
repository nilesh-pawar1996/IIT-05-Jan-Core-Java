package com.sunbeam.backend;

import java.util.LinkedList;

public class MaintenanceService extends Service {
	private static final long serialVersionUID = 1L;
	private double laborCharges;
	private LinkedList<SparePart> partList;
	public MaintenanceService() {
		this("", 0.0);
	}
	public MaintenanceService(String desc, double laborCharges) {
		super(desc);
		this.laborCharges = laborCharges;
		this.partList = new LinkedList<>();
	}
	public double getLaborCharges() {
		return laborCharges;
	}
	public void setLaborCharges(double laborCharges) {
		this.laborCharges = laborCharges;
	}
	public LinkedList<SparePart> getPartList() {
		return partList;
	}
	public void setPartList(LinkedList<SparePart> partList) {
		this.partList = partList;
	}
	public void newSparePart(SparePart part) {
		this.partList.add(part);
	}
	@Override
	public double price() {
		double total = this.laborCharges;
		for (SparePart part : partList) {
			total += part.getRate();
		}
		return total;
	}

	@Override
	public String toString() {
		return "[M] " + super.toString();
	}
}

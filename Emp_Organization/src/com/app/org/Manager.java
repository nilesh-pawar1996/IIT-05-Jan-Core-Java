package com.app.org;

public class Manager extends Employee{
	private double performanceBonus;

	public Manager() {
		super();
		this.performanceBonus=0.0;
	}
	public Manager(int empId, String name, String deptId, double basicsalary) {
		super(empId, name, deptId, basicsalary);
		this.performanceBonus=0.0;
	}
	public Manager(int empId, String name, String deptId, double basicsalary,double performanceBonus) {
		super(empId, name, deptId, basicsalary);
		this.performanceBonus=performanceBonus;
	}
	public double getPerformanceBonus() {
		return performanceBonus;
	}
	public void setPerformanceBonus(double performanceBonus) {
		this.performanceBonus = performanceBonus;
	}
	
	@Override
	public String toString() {
		return super.toString()+"[performanceBonus=" + performanceBonus + "]";
	}

	@Override
	public double computeNetSalary() {
		
		return this.getBasicsalary()+this.performanceBonus;
	}
	
	
}

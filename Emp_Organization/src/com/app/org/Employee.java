package com.app.org;

public class Employee {
	private int empId;
	private String name;
	private String deptId;
	private double basicsalary;
	
	public Employee() {
		this.empId=0;
		this.name=" ";
		this.deptId="00";
		this.basicsalary=0.0;
	}

	public Employee(int empId, String name, String deptId, double basicsalary) {
		super();
		this.empId = empId;
		this.name = name;
		this.deptId = deptId;
		this.basicsalary = basicsalary;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public double getBasicsalary() {
		return basicsalary;
	}

	public void setBasicsalary(double basicsalary) {
		this.basicsalary = basicsalary;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", deptId=" + deptId + ", basicsalary=" + basicsalary
				+ "]";
	}
	
	public double computeNetSalary()
	{
		return 0.0;
	}
	
}

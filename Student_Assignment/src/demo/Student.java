package demo;

import java.util.Scanner;

public class Student {
	private int rollNo;
	private String name;
	private double marks;
	
	
	public Student() {
		this.rollNo=0;
		this.name="";
		this.marks=0.0;
	}
	
	public Student(int rollNo,String name, double marks) {
		this.rollNo=rollNo;
		this.name=name;
		this.marks=marks;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}
	
	
	public void acceptStudentDetails(Scanner sc)
	{
		System.out.println("Enter rollNo ::");
		this.rollNo=sc.nextInt();
		System.out.println("Enter name ::");
		this.name=sc.next();
		System.out.println("Enter marks ::");
		this.marks=sc.nextDouble();
	}
	public void printStudentDetails()
	{
		System.out.println("Roll No ::"+this.rollNo);
		System.out.println("Name ::"+this.name);
		System.out.println("Marks ::"+this.marks);
	}
}

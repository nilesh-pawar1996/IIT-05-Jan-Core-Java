package test;

import java.util.Scanner;

import demo.Student;

public class Tester {
	static Scanner sc = new Scanner(System.in);

	public static int menuList() {

		System.out.println("------Menu----------");
		System.out.println("0.Exit");
		System.out.println("1. Add and accept Student");
		System.out.println("2. Print Student");
		System.out.print("Enter your choice	::");
		return sc.nextInt();
	}

	public static void main(String[] args) {
		int choice = 0;
		Student student=null;
		while ((choice = menuList()) != 0) {
			switch (choice) {
			case 1:
			
				student=new Student();
				student.acceptStudentDetails(sc);
				break;
			case 2:
				student.printStudentDetails();
				break;
			}
		}
	}
}

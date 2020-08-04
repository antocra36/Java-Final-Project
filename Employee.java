package com.cognixia.jump.advancedjava.finalproject;

import java.util.Date;

public class Employee extends Person{
	
	private int id;
	private String department;
	private int hourlyWage;
	private static int counter = 1000;
	private Date hireDate;
	private static int maxId = 0;
	
	public Employee() {
		super("", "", "");
		this.id = counter++;
		this.department = "";
		this.hourlyWage = 0;
	}
	
	public Employee(int id, String firstName, String lastName, String department, int hourlyWage, String birthday) {
		super(firstName, lastName, birthday);
		if (id > maxId) {
			maxId = id;
		}
		this.id = id;
		this.department = department;
		this.hourlyWage = hourlyWage;
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getDepartment() {
		return this.department;
	}
	
	public int getHourlyWage() {
		return this.hourlyWage;
	}
	
	public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public static int generateId() {
		return ++maxId;
	}
}

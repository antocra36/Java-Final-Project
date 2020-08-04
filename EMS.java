package com.cognixia.jump.advancedjava.finalproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EMS {
	
	ArrayList<Employee> employees;
	public static final String CSV_FILE = "resources/EMS.csv";
	public final String headers = "id,first name,last name,hourly wage,department,birthday\n";
	
	public EMS() {
		this.employees = new ArrayList<>();
	}
	
	public boolean add(Employee employee) {
		this.employees.add(employee);
		updateFile();
		return true;
	}
	public boolean update(int id, String field, String value) {
		
		Employee employee = findEmployeeById(id);
		
		if (employee == null) {
			return false;
		}
		
		switch(field) { 
		case "first name":
			employee.setFirstName(value);
			break;
		case "last name":
			employee.setLastName(value);
			break;
		case "department":
			if (!isDepartment(value)) {
				System.out.println("Not a recognized department.");
				System.out.println("Must be one of the following: IT, Sales, Pay Roll, Legal, CEO");
				return false;
			}
			employee.setDepartment(value);
			break;
		case "hourly wage":
			try {
				int newWage = Integer.parseInt(value);
			} catch (NumberFormatException e){
				System.out.println("Not a numeric value. Please try again.");
				return false;
			}
			employee.setHourlyWage(Integer.parseInt(value));
			break;
		case "birthday":
			employee.setBirthday(value);
			break;
		default: 
		}
		System.out.println("Successfully updated.");
		updateFile();
		return true;
	}


	public boolean remove(int id) {
		
		Employee employee = findEmployeeById(id);
		
		if(employee == null) {
			return false;
		}
		
		employees.remove(employee);		
		updateFile();
		
		return true;
	}
	
	public boolean list(int id) {	
		
		Employee employee = findEmployeeById(id);
		
		if(employee == null) {
			return false;
		}
		
		System.out.println("----------------");
		System.out.println("Employee ID: " + employee.getId() + 
							"\nFull Name: " + employee.getFirstName() + " " + employee.getLastName() +
							"\nDepartment: " + employee.getDepartment() + 
							"\nHourly: " + employee.getHourlyWage());
	
		return true;
	}
	
	public boolean list(String department) {	
		
		ArrayList<Employee> employee = findEmployeeByDepartment(department);
		
		if(employee == null) {
			return false;
		}
		
		for(int i = 0; i < employee.size(); i++) {
		System.out.println("----------------");
		System.out.println("Employee ID: " + employee.get(i).getId() + 
							"\nFull Name: " + employee.get(i).getFirstName() + " " + employee.get(i).getLastName() +
							"\nDepartment: " + employee.get(i).getDepartment() + 
							"\nHourly: " + employee.get(i).getHourlyWage());

	}
		return true;
}
	
	public boolean listAll() {
		for (int i = 0; i < employees.size(); i++) {
			
			Employee currentEmployee = employees.get(i);
			
			System.out.println("----------------");
			
			String entry = "Employee ID: " + currentEmployee.getId() + "\n" 
					+ "Full Name: " + currentEmployee.getFirstName() + " " + currentEmployee.getLastName() + "\n" 
					+ "Hourly Wage: " + currentEmployee.getHourlyWage() + "\n"
					+ "Department: " + currentEmployee.getDepartment() +  "\n"
					+ "Birthday: " + currentEmployee.getBirthdayString();  
			
			System.out.println(entry);

		}
		
		return true;
	}
	
	private Employee findEmployeeById(int id) {
		Optional<Employee> employeeOpt = this.employees.stream()
				.filter(e -> e.getId() == id)
				.findFirst();
		
		if (employeeOpt.isPresent()) {
			Employee employee = employeeOpt.get();
			return employee;
		} else {
			return null;
		}
	}
	
	private ArrayList<Employee> findEmployeeByDepartment(String department) {
		ArrayList<Employee> employeeOpt = (ArrayList<Employee>) this.employees.stream()
				.filter(e -> e.getDepartment().equals(department))
				.collect(Collectors.toList());
		
		if (!employeeOpt.isEmpty()) {
			return employeeOpt;
		} else {
			return null;
		}
	}
	
	
	public void updateFile() {
		File file = new File(CSV_FILE);
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
			bw.write(headers);
			
			for (int i = 0; i < employees.size(); i++) {
				
				Employee currentEmployee = employees.get(i);				
				
				String entry = "" + currentEmployee.getId() + "," 
						+ currentEmployee.getFirstName() + "," 
						+ currentEmployee.getLastName() + "," 
						+ currentEmployee.getHourlyWage() + ","
						+ currentEmployee.getDepartment() + "," 
						+ currentEmployee.getBirthdayString() + "\n";  
				
				bw.write(entry);
			}
		} catch (IOException e) {
			System.out.println("File could not be written to.");
		} catch (Exception e) {
			System.out.println("Something went wrong.");
		} finally {
			try {
				if (bw != null) bw.close();
				if (fw != null) fw.close();
			} catch (IOException e) {
				System.out.println("File could not be closed.");
			}
		}
		
	}
	
	public boolean isFound(int id) {
		Optional<Employee> employeeOpt = this.employees.stream()
				.filter(e -> e.getId() == id)
				.findFirst();

		if (employeeOpt.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isField(String field) {
		switch(field) {
		case "first name":
		case "last name":
		case "department":
		case "hourly wage":
		case "birthday":
			return true;
		default:
			return false;
		}
	}
	
	public boolean isDepartment(String dept) {
		switch(dept) {
			case "IT":
			case "Sales":
			case "Pay Roll":
			case "Legal":
			case "CEO":
				return true;
			default: 
				return false;
		}
	}
	
}

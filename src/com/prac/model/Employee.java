package com.prac.model;

public class Employee {
	private int id;
	private String empName;
	private String empMailId;
	private String empContactNumber;
	
	public Employee(){
		
	}
	
	public Employee(String empName, String empMailId, String emplContactNumber){
		this.empName = empName;
		this.empMailId = empMailId;
		this.empContactNumber = emplContactNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpMailId() {
		return empMailId;
	}

	public void setEmpMailId(String empMailId) {
		this.empMailId = empMailId;
	}

	public String getEmpContactNumber() {
		return empContactNumber;
	}

	public void setEmpContactNumber(String empContactNumber) {
		this.empContactNumber = empContactNumber;
	}

	@Override
	public String toString() {
		String data = "{\"empName\":" + empName + ",\"empMailId:\"" + empMailId + ",\"empContactNumber:\""
				+ empContactNumber + "}";
		return data;
	}

}

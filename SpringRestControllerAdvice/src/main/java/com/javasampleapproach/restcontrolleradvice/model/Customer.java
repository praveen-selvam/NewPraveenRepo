package com.javasampleapproach.restcontrolleradvice.model;

public class Customer {

	private String name;
	private int age;
	
	public Customer(String name, int age){
		this.setName(name);
		this.setAge(age);
	}

	// name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// age
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

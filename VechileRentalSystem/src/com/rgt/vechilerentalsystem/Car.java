package com.rgt.vechilerentalsystem;

public class Car extends Vehicle {

	private int numOfDoors;

	public Car(String make, String model, String licensePlate, int numOfDoors) {
		super(make, model, licensePlate);
		this.numOfDoors = numOfDoors;
	}

	public int getNumOfDoors() {
		return numOfDoors;
	}

	public void setNumOfDoors(int numOfDoors) {
		this.numOfDoors = numOfDoors;
	}
	
	

}

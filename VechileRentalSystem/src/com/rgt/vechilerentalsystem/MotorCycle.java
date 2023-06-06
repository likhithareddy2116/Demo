package com.rgt.vechilerentalsystem;

public class MotorCycle extends Vehicle {

	private int numOfSeating;

	public MotorCycle(String make, String model, String licensePlate, int numOfSeating) {
		super(make, model, licensePlate);
		this.numOfSeating = numOfSeating;
	}

	public int getNumOfSeating() {
		return numOfSeating;
	}

	public void setNumOfSeating(int numOfSeating) {
		this.numOfSeating = numOfSeating;
	}
	

	
}

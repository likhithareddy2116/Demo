package com.rgt.vechilerentalsystem;

public class Bicycle extends Vehicle {

	private int numOfBreaks;

	public Bicycle(String make, String model, String licensePlate, int numOfBreaks) {
		super(make, model, licensePlate);
		this.numOfBreaks = numOfBreaks;
	}

	public int getNumOfBreaks() {
		return numOfBreaks;
	}

	public void setNumOfBreaks(int numOfBreaks) {
		this.numOfBreaks = numOfBreaks;
	}

	
}
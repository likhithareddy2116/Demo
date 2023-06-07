package com.rgt.vechilerentalsystem;

public abstract class Vehicle implements VechileOperations {

	private String make;
	private String model;
	private String licensePlate;
	private boolean isAvailable;

	public Vehicle(String make, String model, String licensePlate) {
		this.make = make;
		this.model = model;
		this.licensePlate = licensePlate;
		this.isAvailable = true;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
    
	@Override
	public boolean isAvailable() {
		return isAvailable;
	}
    @Override
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}

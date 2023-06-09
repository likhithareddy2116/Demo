package com.rgt.vechilerentalsystem;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalService implements RentalCostCalculator {

	private List<Vehicle> vehicles;
	private List<Rental> rentalss;

	public RentalService() {
		this.vehicles = new ArrayList<>();
		this.rentalss = new ArrayList<>();

	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	public void remove(Vehicle vehicle) {
		vehicles.remove(vehicle);
	}

	/**
	 * 
	 * fetching list of available vehicles
	 */
	public List<Vehicle> displayGetAvailableVehiles() {
		List<Vehicle> listOfVechiles = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.isAvailable()) {
				listOfVechiles.add(vehicle);
			}
		}
		return listOfVechiles;
	}

	public Rental rentVehicle(Customer customer, Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
		if (vehicle.isAvailable()) {
			Rental rental = new Rental(vehicle, customer, startTime, endTime);
			rentalss.add(rental);
			vehicle.setAvailable(false);
			return rental;
		} else {
			return null;
		}

	}

	/**
	 * overide method from interface
	 * 
	 * calculating rental cost based on the price of vehicle and num of days
	 */
	@Override
	public BigDecimal calculateRentalCost(Rental rental) {
		Duration duration = Duration.between(rental.getStartTime(), rental.getEndTime());
		long hours = duration.toHours();
		BigDecimal hourlyRate = BigDecimal.valueOf(10);
		return hourlyRate.multiply(BigDecimal.valueOf(hours));
	}

	// Returned a Rental Vechile
	public boolean returnVehicle(Rental rental) {
		if (rentalss.contains(rental)) {
			rentalss.remove(rental);
			rental.getRentedVehicle().setAvailable(true);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * fetching rental by rentalId
	 * 
	 */
	public Rental getRentalById(String rentalId) {
		for (Rental rental : rentalss) {
			if (rental.getRentalId().equals(rentalId)) {
				return rental;
			}
		}
		return null;
	}

	/**
	 * 
	 * fetching a vehicle based on licensePlate
	 * 
	 */
	public Vehicle getVehicleByLicensePlate(String licensePlate) {
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getLicensePlate().equals(licensePlate)) {
				return vehicle;
			}
		}
		return null;
	}
}

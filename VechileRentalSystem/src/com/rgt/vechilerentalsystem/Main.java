package com.rgt.vechilerentalsystem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		RentalService rentalService = new RentalService();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Vechile Rental System menu :");
			System.out.println("1. Add Vehicle");
			System.out.println("2. List Available vehicles");
			System.out.println("3. Rent Vehicle");
			System.out.println("4. Calculate rental cost");
			System.out.println("5. Return vehicle");
			System.out.println("6.Quit");
			System.out.print("Choose an option: ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				addVehicle(rentalService, scanner);
				break;
			case 2:
				listAvailableVehicles(rentalService);
				break;
			case 3:
				rentVehicle(rentalService, scanner);
				break;
			case 4:
				calculateRentalCost(rentalService, scanner);
				break;
			case 5:
				returnVehicle(rentalService, scanner);

				break;
			case 6:
				System.out.println("Quit");
				break;
			default:
				System.out.println("Invalid Choice");
			}
			System.out.println();
		}

	}

	private static void addVehicle(RentalService rentalService, Scanner scanner) {
		System.out.println("Enter the type of vehicle (car, motorcycle, bicycle): ");
		String type = scanner.next();

		System.out.println("Enter the license plate: ");
		String licensePlate = scanner.next();

		System.out.println("Enter the make: ");
		String make = scanner.next();

		System.out.println("Enter the model: ");
		String model = scanner.next();

		Vehicle vehicle;
		switch (type) {
		case "car":
			System.out.println("Enter the number of doors: ");
			int numOfDoors = scanner.nextInt();
			vehicle = new Car(licensePlate, make, model, numOfDoors);
			break;
		case "motorcycle":
			System.out.println("Enter Number of Seating: ");
			int numOfSeating = scanner.nextInt();
			vehicle = new MotorCycle(licensePlate, make, model, numOfSeating);
			break;
		case "bicycle":
			System.out.println("Enter the number of breaks: ");
			int numOfBreaks = scanner.nextInt();
			vehicle = new Bicycle(licensePlate, make, model, numOfBreaks);
			break;
		default:
			System.out.println("Invalid vehicle type. Vehicle not added.");
			return;
		}

		rentalService.addVehicle(vehicle);
		System.out.println("Vehicle added successfully.");
	}

	private static void listAvailableVehicles(RentalService rentalService) {
		List<Vehicle> availableVehicles = rentalService.displayGetAvailableVehiles();
		if (availableVehicles.isEmpty()) {
			System.out.println(" vehicles not available for rent.");
		} else {
			System.out.println("Available Vehicles:");
			for (Vehicle vehicle : availableVehicles) {
				System.out.println("License Plate: " + vehicle.getLicensePlate());
				System.out.println("Make: " + vehicle.getMake());
				System.out.println("Model: " + vehicle.getModel());
				System.out.println();
			}
		}
	}

	private static void rentVehicle(RentalService rentalService, Scanner scanner) {
		System.out.println("Enter your first name: ");
		String firstName = scanner.next();

		System.out.println("Enter your last name: ");
		String lastName = scanner.next();

		System.out.println("Enter your ID: ");
		String customerId = scanner.next();

		System.out.println("Enter the license plate of the vehicle you want to rent: ");
		String licensePlate = scanner.next();

		System.out.println("Enter the start date and time (yyyy-MM-dd HH:mm): ");
		String startDateTimeStr = scanner.next();
		LocalDateTime startTime;

		try {
			startTime = LocalDateTime.parse(startDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date-time format. Please enter in the format yyyy-MM-dd HH:mm.");
			return;
		}

		System.out.println("Enter the end date and time (yyyy-MM-dd HH:mm): ");
		String endDateTimeStr = scanner.next();
		LocalDateTime endTime;

		try {
			endTime = LocalDateTime.parse(endDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date-time format. Please enter in the format yyyy-MM-dd HH:mm.");
			return;
		}

		Customer customer = new Customer(customerId, firstName, lastName);
		Vehicle selectedVehicle = rentalService.getVehicleByLicensePlate(licensePlate);

		if (selectedVehicle != null) {
			Rental rental = rentalService.rentVechile(customer, selectedVehicle, startTime, endTime);

			if (rental != null) {
				System.out.println("Rental successful!");
			} else {
				System.out.println("The selected vehicle is not available for rent.");
			}
		} else {
			System.out.println("Invalid license plate. Please try again.");
		}
	}

	private static void calculateRentalCost(RentalService rentalService, Scanner scanner) {
		System.out.print("Enter the rental ID: ");
		String rentalId = scanner.nextLine();

		Rental rental = rentalService.getRentalById(rentalId);
		if (rental != null) {
			BigDecimal rentalCost = rentalService.calculateRentalCost(rental);
			System.out.println("Rental Cost: $" + rentalCost);
		} else {
			System.out.println("Invalid rental ID.");
		}
	}

	private static void returnVehicle(RentalService rentalService, Scanner scanner) {
		System.out.print("Enter the rental ID: ");
		String rentalId = scanner.nextLine();

		Rental rental = rentalService.getRentalById(rentalId);
		if (rental != null) {
			boolean success = rentalService.returnVehicle(rental);
			if (success) {
				System.out.println("Vehicle returned successfully.");
			} else {
				System.out.println("Failed to return the vehicle.");
			}
		} else {
			System.out.println("Invalid rental ID.");
		}
	}

}

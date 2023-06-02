package com.rgt.simpleLibrary;

import java.util.Scanner;

public class SimpleLibrary {

	public static void main(String[] args) {
		Book system = new Book();
		Patron parton = new Patron();
		Scanner scanner = new Scanner(System.in);
		Library library = new Library();
		int choice;

		while (true) {
			do {
				System.out.println("Library Management System");
				System.out.println("1. Add Book");
				System.out.println("2. Add Patron");
				System.out.println("3. Barrow Book");
				System.out.println("4. Return Book");
				System.out.println("5. Exit");
				System.out.print("Choose an option: ");
				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter Book Title:");
					String title = scanner.next();
					System.out.println("Enter Author Name:");
					String author = scanner.next();
					system = new Book(title, author);
					library.addBook(system);
					break;
				case 2:
					System.out.println("Enter Patron name:");
					String name = scanner.next();
					parton = new Patron(name);
					library.addPatron(parton);
					break;
				case 3:
					System.out.println("Enter Parton name:");
					String names = scanner.next();
					System.out.println("Enter book title:");
					String titles = scanner.next();
					library.barrowBook(names, titles);
					break;
				case 4:
					System.out.println("Enter Parton name:");
					String namee = scanner.next();
					System.out.println("Enter book title:");
					String titless = scanner.next();
					library.returnBook(namee, titless);
					break;
				case 5:
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Invalid option. Please try again.");
				}
				System.out.println();

			} while (choice != 5);
			break;

		}
	}

}

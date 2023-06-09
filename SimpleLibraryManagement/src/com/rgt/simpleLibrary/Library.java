package com.rgt.simpleLibrary;

public class Library {
	private static final int MAX_BOOKS = 5;
	private static final int MAX_PATRONS = 5;

	private Book[] books;
	private Patron[] patrons;
	private int bookCount;
	private int patronsCount;

	public Library() {
		books = new Book[MAX_BOOKS];
		patrons = new Patron[MAX_PATRONS];
		bookCount = 0;
		patronsCount = 0;
	}

	/**
	 * 
	 * adding the Book
	 */
	public void addBook(Book book) {
		if (bookCount < MAX_BOOKS) {
			books[bookCount++] = book;
			System.out.println("Book added to the library.");
		} else {
			System.out.println("Books Limt over.");
		}
	}

	/**
	 * 
	 * Adding the patron
	 */
	public void addPatron(Patron patron) {
		if (patronsCount < MAX_PATRONS) {
			patrons[patronsCount++] = patron;
			System.out.println("Patron Added successfully");
		} else {
			System.out.println("cannot added more");
		}

	}

	/**
	 * 
	 * Borrow the book based on title and name
	 * 
	 */
	public void barrowBook(String names, String titles) {
		Book book = findBook(titles);
		Patron patron = findPatron(names);
		if (book != null && patron != null) {
			if (!book.isAvailable()) {
				System.out.println("Book is already borrowed by another patron");

			} else {
				book.setAvailable(false);
				book.setBarrower(patron);
				System.out.println("Book borrowed successfully.");
			}
		} else {
			System.out.println("Book and parton is not available");

		}
	}

	/**
	 * 
	 * findPatron based on name
	 * 
	 */
	private Patron findPatron(String name) {
		for (int i = 0; i < patronsCount; i++) {
			if (patrons[i].getName().equalsIgnoreCase(name)) {
				return patrons[i];
			}
		}
		return null;
	}

	/**
	 * 
	 * findBook based on title
	 * 
	 */
	private Book findBook(String title) {
		for (int i = 0; i < bookCount; i++) {
			if (books[i].getTitle().equalsIgnoreCase(title)) {
				return books[i];
			}
		}
		return null;
	}

	/**
	 * 
	 * return book from based on name and title
	 * 
	 */
	public void returnBook(String namee, String title) {
		Book book = findBook(title);
		Patron patron = findPatron(namee);
		if (book != null && patron != null) {
			if (!book.isAvailable()) {
				book.setAvailable(true);
				book.setBarrower(null);
				System.out.println("Book returned successfully");
			} else {
				System.out.println("Book already returned ");
			}

		} else {
			System.out.println("There is no books to return");
		}

	}
}

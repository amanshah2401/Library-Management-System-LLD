package com.systemdesign.Library.Management.System;

import com.systemdesign.Library.Management.System.enums.BookFormat;
import com.systemdesign.Library.Management.System.enums.BookStatus;
import com.systemdesign.Library.Management.System.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
        driverCode();
    }

    public static void driverCode(){
        // Setup address & library
        Address libAddr = new Address("123 Library Ln", "", "Delhi", "Delhi", "India", "110001");
        Library central = new Library("Central Library", libAddr);

        // Add librarian & member
        Librarian lib = new Librarian("Rita", "rita@lib.com", "9999999999");
        central.addLibrarian(lib);
        Member aman = new Member("Aman Shah", "aman@example.com", "8888888888");
        central.registerMember(aman);

        // Create author and book
        Address authorAddr = new Address("1 Writer St", "", "Mumbai", "Maharashtra", "India", "400001");
        Author author = new Author("Aravind", "aravind@example.com", "7777777777", authorAddr, "Technical author");
        Book book = lib.addBook("ISBN-12345", "Data Structures in Java", "TechPress", LocalDate.of(2020, 5, 1),
                Arrays.asList("Computer Science", "Programming"), "A DS book", Arrays.asList(author));
        central.addBookToCatalog(book);

        // Add a rack and book items
        Rack rack1 = central.findOrCreateRack("Floor1-Aisle1-ShelfA");
        BookItem copy1 = lib.addBookItem(book, BookFormat.HARDCOVER, rack1);
        rack1.placeBookItem(copy1);
        central.addRack(rack1);

        // Search
        System.out.println("\nSearch by title 'Data': ");
        for (Book b : central.getCatalog().searchByTitle("Data")) {
            System.out.println(" - " + b);
        }

        // Member reserves when available
        BookReservation res = aman.reserveBook(copy1); // should set to PENDING and change status to RESERVED

        // Member tries to checkout (should fail because reserved)
        BookLending lendFail = aman.checkoutBook(copy1, 14);

        // Librarian un-reserve (simulate making it available again) and member checks out
        copy1.setStatus(BookStatus.AVAILABLE);
        // Member now checks out
        BookLending lending = aman.checkoutBook(copy1, 7);

        // Wait simulation - didn't actually wait; show renewal and return flows
        if (lending != null) {
            // renew
            aman.renewBook(lending, 7);

            // return
            aman.returnBook(lending);
        }

        // Demonstrate notifications when an item becomes available and observers were registered
        BookItem copy2 = lib.addBookItem(book, BookFormat.PAPERBACK, central.findOrCreateRack("Floor1-Aisle1-ShelfA"));
        rack1.placeBookItem(copy2);
        central.addRack(rack1);
        // make copy2 not available and register observer
        copy2.setStatus(BookStatus.LOANED);
        BookReservation r2 = aman.reserveBook(copy2); // registers observer and status WAITING
        // Now item becomes available:
        copy2.setStatus(BookStatus.AVAILABLE); // that triggers notification to observer

        System.out.println("\nDriver finished.");
    }
}



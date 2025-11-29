package com.systemdesign.Library.Management.System.model;


import com.systemdesign.Library.Management.System.enums.BookFormat;

import java.time.LocalDate;
import java.util.List;

public class Librarian extends User {

    public Librarian(String name, String email, String phone) {
        super(name, email, phone);
    }

    // librarian responsibilities: add book items, block/unblock members
    public Book addBook(String isbn, String title, String publisher, LocalDate pubDate, List<String> subjects, String description, List<Author> authors) {
        return new Book(isbn, title, publisher, pubDate, subjects, description, authors);
    }

    public BookItem addBookItem(Book book, BookFormat format, Rack rack) {
        return new BookItem(book, format, rack);
    }

    public void blockMember(Member m) {
        m.blockAccount();
        System.out.println("Librarian " + name + " blocked member " + m.getName());
    }

    public void unblockMember(Member m) {
        m.unblockAccount();
        System.out.println("Librarian " + name + " unblocked member " + m.getName());
    }}

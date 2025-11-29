package com.systemdesign.Library.Management.System.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalog implements Search {
    private List<Book> books = new ArrayList<>();

    public Catalog() {}

    public void addBook(Book b) {
        books.add(b);
    }

    public void removeBook(Book b) {
        books.remove(b);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        List<Book> res = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) res.add(b);
        }
        return res;
    }

    @Override
    public List<Book> searchByAuthor(String authorName) {
        List<Book> res = new ArrayList<>();
        for (Book b : books) {
            for (Author a : b.getAuthors()) {
                if (a.getName().toLowerCase().contains(authorName.toLowerCase())) {
                    res.add(b);
                    break;
                }
            }
        }
        return res;
    }

    @Override
    public List<Book> searchBySubject(String subject) {
        List<Book> res = new ArrayList<>();
        for (Book b : books) {
            for (String s : b.getSubjects()) {
                if (s.toLowerCase().contains(subject.toLowerCase())) {
                    res.add(b);
                    break;
                }
            }
        }
        return res;
    }

    @Override
    public List<Book> searchByPublicationDate(LocalDate publicationDate) {
        List<Book> res = new ArrayList<>();
        for (Book b : books) {
            if (b.getPublicationDate().equals(publicationDate)) res.add(b);
        }
        return res;
    }

    public List<Book> getAllBooks() { return Collections.unmodifiableList(books); }
}


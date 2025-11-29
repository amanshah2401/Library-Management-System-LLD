package com.systemdesign.Library.Management.System.model;

import com.systemdesign.Library.Management.System.enums.BookFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private String publisher;
    private LocalDate publicationDate;
    private List<String> subjects;
    private String description;
    private List<Author> authors;
    // aggregation: Book -> many BookItems (handled elsewhere)

    public Book(String isbn, String title, String publisher, LocalDate publicationDate,
                List<String> subjects, String description, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.subjects = new ArrayList<>(subjects);
        this.description = description;
        this.authors = new ArrayList<>(authors);
    }

    // getters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getPublisher() { return publisher; }
    public LocalDate getPublicationDate() { return publicationDate; }
    public List<String> getSubjects() { return Collections.unmodifiableList(subjects); }
    public String getDescription() { return description; }
    public List<Author> getAuthors() { return Collections.unmodifiableList(authors); }

    @Override
    public String toString() {
        return String.format("Book[%s - %s]", isbn, title);
    }
}
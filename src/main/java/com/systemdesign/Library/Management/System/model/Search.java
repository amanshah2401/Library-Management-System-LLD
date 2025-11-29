package com.systemdesign.Library.Management.System.model;

import java.time.LocalDate;
import java.util.List;

interface Search {
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String authorName);
    List<Book> searchBySubject(String subject);
    List<Book> searchByPublicationDate(LocalDate publicationDate);
}


package com.systemdesign.Library.Management.System.model;

import com.systemdesign.Library.Management.System.enums.BookFormat;
import com.systemdesign.Library.Management.System.enums.BookStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BookItem {
    private String itemId;            // unique id for this copy
    private Book book;               // composition: BookItem has Book reference
    private BookFormat format;
    private BookStatus status;
    private Rack rack;               // physical location; may be null for e-books
    private LocalDate addedDate;

    // Observers for availability changes (simple observer pattern)
    private List<Member> observers = new ArrayList<>();

    public BookItem(Book book, BookFormat format, Rack rack) {
        this.itemId = UUID.randomUUID().toString();
        this.book = book;
        this.format = format;
        this.rack = rack;
        this.status = BookStatus.AVAILABLE;
        this.addedDate = LocalDate.now();
        if (rack != null) rack.placeBookItem(this);
    }

    public String getItemId() { return itemId; }
    public Book getBook() { return book; }
    public BookFormat getFormat() { return format; }
    public BookStatus getStatus() { return status; }
    public Rack getRack() { return rack; }
    public LocalDate getAddedDate() { return addedDate; }

    public void setStatus(BookStatus status) {
        this.status = status;
        if (status == BookStatus.AVAILABLE) notifyObservers();
    }

    // Observer pattern methods
    public void registerObserver(Member m) {
        if (!observers.contains(m)) observers.add(m);
    }
    public void unregisterObserver(Member m) {
        observers.remove(m);
    }
    private void notifyObservers() {
        for (Member m : new ArrayList<>(observers)) {
            EmailNotification n = new EmailNotification("Availability Update: " + book.getTitle(),
                    "The item " + book.getTitle() + " is now AVAILABLE.", m.getEmail());
            n.send();
        }
    }

    @Override
    public String toString() {
        return "BookItem{" + book.getTitle() + " | " + itemId.substring(0,6) + " | " + status + "}";
    }
}

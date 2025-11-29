package com.systemdesign.Library.Management.System.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

public class BookLending {
    private String lendingId;
    private Member member;
    private BookItem bookItem;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate; // null if not returned

    public BookLending(Member member, BookItem bookItem, LocalDate checkoutDate, LocalDate dueDate) {
        this.lendingId = UUID.randomUUID().toString();
        this.member = member;
        this.bookItem = bookItem;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public String getLendingId() { return lendingId; }
    public Member getMember() { return member; }
    public BookItem getBookItem() { return bookItem; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDate d) { this.returnDate = d; }
    public void setDueDate(LocalDate d) { this.dueDate = d; }

    public boolean isReturned() { return returnDate != null; }
    public boolean isLate() {
        LocalDate ref = isReturned() ? returnDate : LocalDate.now();
        return ref.isAfter(dueDate);
    }
    public long daysLate() {
        if (!isLate()) return 0;
        LocalDate ref = isReturned() ? returnDate : LocalDate.now();
        return ChronoUnit.DAYS.between(dueDate, ref);
    }
    public double calculateFine() {
        long days = daysLate();
        double perDay = 1.0; // simple flat fine per day
        return days * perDay;
    }

    @Override
    public String toString() {
        return "Lending[" + lendingId.substring(0,6) + " book:" + bookItem.getBook().getTitle() + " due:" + dueDate + "]";
    }
}

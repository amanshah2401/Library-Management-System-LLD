package com.systemdesign.Library.Management.System.model;

import com.systemdesign.Library.Management.System.enums.AccountStatus;
import com.systemdesign.Library.Management.System.enums.BookStatus;
import com.systemdesign.Library.Management.System.enums.ReservationStatus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Member extends User {
    private int totalBooksCheckedOut = 0;
    private List<BookLending> lendings = new ArrayList<>();
    private List<BookReservation> reservations = new ArrayList<>();

    public Member(String name, String email, String phone) {
        super(name, email, phone);
    }

    public int getTotalBooksCheckedOut() { return totalBooksCheckedOut; }
    public List<BookLending> getLendings() { return Collections.unmodifiableList(lendings); }
    public List<BookReservation> getReservations() { return Collections.unmodifiableList(reservations); }

    // Member actions
    public BookReservation reserveBook(BookItem item) {
        if (!card.isActive() || accountStatus != AccountStatus.ACTIVE) {
            System.out.println("Cannot reserve - card inactive or account not active.");
            return null;
        }
        BookReservation r = new BookReservation(this, item);
        reservations.add(r);// used by BookItem observer notification: just exposed email getter
// if item not available, register for notification
        if (item.getStatus() != BookStatus.AVAILABLE) {
            item.registerObserver(this);
            r.setStatus(ReservationStatus.WAITING);
        } else {
            r.setStatus(ReservationStatus.PENDING);
            item.setStatus(BookStatus.RESERVED);
        }
        System.out.println(this.name + " reserved " + item.getBook().getTitle() + " (status: " + r.getStatus() + ")");
        return r;
    }

    public BookLending checkoutBook(BookItem item, int loanDays) {
        if (!card.isActive() || accountStatus != AccountStatus.ACTIVE) {
            System.out.println("Cannot checkout - card inactive or account not active.");
            return null;
        }
        if (item.getStatus() == BookStatus.LOANED || item.getStatus() == BookStatus.RESERVED) {
            System.out.println("Item not available for checkout.");
            return null;
        }
        BookLending bl = new BookLending(this, item, LocalDate.now(), LocalDate.now().plusDays(loanDays));
        lendings.add(bl);
        totalBooksCheckedOut++;
        item.setStatus(BookStatus.LOANED);
        System.out.println(this.name + " checked out " + item.getBook().getTitle() + " due on " + bl.getDueDate());
        return bl;
    }

    public void returnBook(BookLending lending) {
        if (!lendings.contains(lending)) {
            System.out.println("This lending is not owned by the member.");
            return;
        }
        lending.setReturnDate(LocalDate.now());
        lending.getBookItem().setStatus(BookStatus.AVAILABLE);
        totalBooksCheckedOut = Math.max(0, totalBooksCheckedOut - 1);
        System.out.println(name + " returned " + lending.getBookItem().getBook().getTitle());
        // charge fine if late
        if (lending.isLate()) {
            long daysLate = lending.daysLate();
            double fine = lending.calculateFine();
            System.out.println("Late by " + daysLate + " days. Fine: " + fine);
            // send notification
            EmailNotification n = new EmailNotification("Late return fine", "You have a fine of " + fine + " for late return.", email);
            n.send();
        }
        lendings.remove(lending);
    }

    public boolean renewBook(BookLending lending, int extraDays) {
        if (!lendings.contains(lending)) { return false; }
        if (lending.isReturned()) {
            System.out.println("Cannot renew - already returned.");
            return false;
        }
        // Simple rule: allow renew if not already late
        if (lending.isLate()) {
            System.out.println("Cannot renew - already late.");
            return false;
        }
        LocalDate newDue = lending.getDueDate().plusDays(extraDays);
        lending.setDueDate(newDue);
        System.out.println(name + " renewed " + lending.getBookItem().getBook().getTitle() + " new due: " + newDue);
        return true;
    }

    // used by BookItem observer notification: just exposed email getter

}

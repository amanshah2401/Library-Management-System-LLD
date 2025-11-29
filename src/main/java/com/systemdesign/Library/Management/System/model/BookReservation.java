package com.systemdesign.Library.Management.System.model;

import com.systemdesign.Library.Management.System.enums.ReservationStatus;

import java.time.LocalDate;
import java.util.UUID;

public class BookReservation {
    private String reservationId;
    private Member member;
    private BookItem item;
    private LocalDate reservedOn;
    private ReservationStatus status;

    public BookReservation(Member member, BookItem item) {
        this.reservationId = UUID.randomUUID().toString();
        this.member = member;
        this.item = item;
        this.reservedOn = LocalDate.now();
        this.status = ReservationStatus.NONE;
    }

    public String getReservationId() { return reservationId; }
    public Member getMember() { return member; }
    public BookItem getItem() { return item; }
    public LocalDate getReservedOn() { return reservedOn; }
    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Reservation[" + reservationId.substring(0,6) + " " + item.getBook().getTitle() + " by " + member.getName() + " status:" + status + "]";
    }
}

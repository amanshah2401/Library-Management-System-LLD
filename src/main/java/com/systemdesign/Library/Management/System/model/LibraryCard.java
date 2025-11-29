package com.systemdesign.Library.Management.System.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class LibraryCard {
    private String cardId;
    private LocalDate issueDate;
    private boolean isActive;

    public LibraryCard() {
        this.cardId = UUID.randomUUID().toString();
        this.issueDate = LocalDate.now();
        this.isActive = true;
    }

    public String getCardId() { return cardId; }
    public LocalDate getIssueDate() { return issueDate; }
    public boolean isActive() { return isActive; }
    public void deactivate() { isActive = false; }
    public void activate() { isActive = true; }

    @Override
    public String toString() {
        return "LibraryCard{" + cardId.substring(0,6) + (isActive? " ACTIVE":" INACTIVE") + "}";
    }

}

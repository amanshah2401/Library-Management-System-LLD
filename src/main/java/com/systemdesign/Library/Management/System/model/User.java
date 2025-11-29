package com.systemdesign.Library.Management.System.model;



import com.systemdesign.Library.Management.System.enums.AccountStatus;

import java.util.UUID;

abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String phone;
    protected LibraryCard card;
    protected AccountStatus accountStatus;

    public User(String name, String email, String phone) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.card = new LibraryCard();
        this.accountStatus = AccountStatus.ACTIVE;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LibraryCard getCard() { return card; }
    public AccountStatus getAccountStatus() { return accountStatus; }

    public void closeAccount() {
        this.accountStatus = AccountStatus.CLOSED;
        this.card.deactivate();
    }

    public void blockAccount() {
        this.accountStatus = AccountStatus.BLOCKED;
    }

    public void unblockAccount() {
        this.accountStatus = AccountStatus.ACTIVE;
    }
}

package com.systemdesign.Library.Management.System.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

abstract class Notification {
    protected String notificationId;
    protected LocalDate createdOn;
    protected String content;

    public Notification(String content) {
        this.notificationId = UUID.randomUUID().toString();
        this.createdOn = LocalDate.now();
        this.content = content;
    }

    public abstract void send();
}


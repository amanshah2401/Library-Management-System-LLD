package com.systemdesign.Library.Management.System.model;

class PostalNotification extends Notification {
    private Address address;

    public PostalNotification(String subject, String content, Address address) {
        super(subject + " - " + content);
        this.address = address;
    }

    @Override
    public void send() {
        System.out.println("[POSTAL] To: " + address + " | " + content + " | CreatedOn: " + createdOn);
    }
}

package com.systemdesign.Library.Management.System.model;

class EmailNotification extends Notification {
    private String toEmail;

    public EmailNotification(String subject, String content, String toEmail) {
        super(subject + " - " + content);
        this.toEmail = toEmail;
    }

    @Override
    public void send() {
        System.out.println("[EMAIL] To: " + toEmail + " | " + content + " | SentOn: " + createdOn);
    }
}



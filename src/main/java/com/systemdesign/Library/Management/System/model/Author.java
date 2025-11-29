package com.systemdesign.Library.Management.System.model;

import java.util.UUID;

public class Author extends Person {
    private String description;

    public Author(String name, String email, String phone, Address address, String description) {
        super(name, email, phone, address);
        this.description = description;
    }

    public String getDescription() { return description; }}

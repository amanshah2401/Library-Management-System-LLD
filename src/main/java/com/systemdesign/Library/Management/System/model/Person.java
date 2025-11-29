package com.systemdesign.Library.Management.System.model;

import lombok.Getter;

@Getter
public class Person {
    protected String name;
    public String email;
    protected String phoneNumber;
    public Address address;

    public Person(String name, String email, String phoneNumber, Address address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public String getName(){
        return this.name;
    }
}

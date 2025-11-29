package com.systemdesign.Library.Management.System.model;

public class Address {
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    public Address(String line1, String line2, String city, String state, String country, String postalCode) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
    }

    public String fullAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(line1);
        if (line2 != null && !line2.isEmpty()) sb.append(", ").append(line2);
        sb.append(", ").append(city).append(", ").append(state).append(", ").append(country).append(" - ").append(postalCode);
        return sb.toString();
    }

    @Override
    public String toString() {
        return fullAddress();
    }
}

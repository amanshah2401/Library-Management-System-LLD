package com.systemdesign.Library.Management.System.model;

import java.util.*;

public class Rack {
    private String rackId;
    private String locationIdentifier; // e.g., "Floor1-Aisle3-Shelf2"
    private Set<BookItem> items;

    public Rack(String locationIdentifier) {
        this.rackId = UUID.randomUUID().toString();
        this.locationIdentifier = locationIdentifier;
        this.items = new HashSet<>();
    }

    public void placeBookItem(BookItem b) {
        items.add(b);
    }

    public void removeBookItem(BookItem b) {
        items.remove(b);
    }

    public String getLocationIdentifier() { return locationIdentifier; }
    public Set<BookItem> getItems() { return Collections.unmodifiableSet(items); }
    public String getRackId() { return rackId; }

    @Override
    public String toString() {
        return "Rack{" + locationIdentifier + "}";
    }
}

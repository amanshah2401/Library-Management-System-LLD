package com.systemdesign.Library.Management.System.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.systemdesign.Library.Management.System.enums.BookStatus;
public class Library {
        private String name;
        private Address address;
        private Catalog catalog;
        private List<BookItem> items = new ArrayList<>();
        private List<Member> members = new ArrayList<>();
        private List<Librarian> librarians = new ArrayList<>();
        private List<Rack> racks = new ArrayList<>();

        public Library(String name, Address address) {
            this.name = name;
            this.address = address;
            this.catalog = new Catalog();
        }

        // basic management
        public void registerMember(Member m) {
            members.add(m);
            System.out.println("Registered member " + m.getName());
        }

        public void addLibrarian(Librarian l) {
            librarians.add(l);
            System.out.println("Added librarian " + l.getName());
        }

        public void addRack(Rack r) {
            racks.add(r);
        }

        public Rack findOrCreateRack(String loc) {
            for (Rack r : racks) {
                if (r.getLocationIdentifier().equals(loc)) return r;
            }
            Rack nr = new Rack(loc);
            addRack(nr);
            return nr;
        }

        public void addBookToCatalog(Book b) {
            catalog.addBook(b);
        }

        public BookItem addBookItem(BookItem bi) {
            items.add(bi);
            return bi;
        }

        public List<BookItem> findBookItemsByBook(Book book) {
            List<BookItem> res = new ArrayList<>();
            for (BookItem bi : items) {
                if (bi.getBook().equals(book)) res.add(bi);
            }
            return res;
        }

        public Catalog getCatalog() { return catalog; }
        public Address getAddress() { return address; }
        public String getName() { return name; }
}

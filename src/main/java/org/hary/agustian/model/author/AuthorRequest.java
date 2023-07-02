package org.hary.agustian.model.author;

import org.hary.agustian.entity.Book;

import java.util.List;

public class AuthorRequest {
    private String name;
    private String email;
    private String telephone;
    private String address;
    private List<Book> books;

    public AuthorRequest() {
    }

    public AuthorRequest(String name, String email, String telephone, String address, List<Book> books) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

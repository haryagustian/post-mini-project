package org.hary.agustian.model.publisher;

import org.hary.agustian.entity.Book;

import java.util.List;

public class PublisherResponse {
    private Integer id;
    private String name;
    private String email;
    private String telephone;
    private String address;
    private List<Book> books;

    public PublisherResponse() {
    }

    public PublisherResponse(Integer id, String name, String email, String telephone, String address, List<Book> books) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

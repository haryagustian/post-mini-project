package org.hary.agustian.entity;

import jakarta.persistence.*;
import org.hary.agustian.util.TimeBase;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends TimeBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String telephone;
    private String address;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Book.class, mappedBy = "authors")
    private List<Book> books;

    public Author() {
    }

    public Author(String name, String email, String telephone, String address, List<Book> books) {
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

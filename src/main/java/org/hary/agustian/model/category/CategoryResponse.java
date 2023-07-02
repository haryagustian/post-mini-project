package org.hary.agustian.model.category;

import org.hary.agustian.entity.Book;

import java.util.List;

public class CategoryResponse {
    private Integer id;
    private String name;
    private List<Book> books;

    public CategoryResponse() {
    }

    public CategoryResponse(Integer id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

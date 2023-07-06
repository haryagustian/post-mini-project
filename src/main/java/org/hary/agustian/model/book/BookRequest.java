package org.hary.agustian.model.book;

import org.hary.agustian.entity.Author;
import org.hary.agustian.entity.BorrowerDetail;
import org.hary.agustian.entity.Category;
import org.hary.agustian.entity.Publisher;

import java.time.Year;
import java.util.List;

public class BookRequest {
    private String title;
    private Year year;
    private Integer quantity;
    private Integer borrowPrice;
    private Category category;
    private List<BorrowerDetail> borrowerDetails;
    private List<Author> authors;
    private Publisher publisher;

    public BookRequest() {
    }

    public BookRequest(String title, Year year, Integer quantity, Integer borrowPrice, Category category, List<BorrowerDetail> borrowerDetails, List<Author> authors, Publisher publisher) {
        this.title = title;
        this.year = year;
        this.quantity = quantity;
        this.borrowPrice = borrowPrice;
        this.category = category;
        this.borrowerDetails = borrowerDetails;
        this.authors = authors;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBorrowPrice() {
        return borrowPrice;
    }

    public void setBorrowPrice(Integer borrowPrice) {
        this.borrowPrice = borrowPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<BorrowerDetail> getBorrowerDetails() {
        return borrowerDetails;
    }

    public void setBorrowerDetails(List<BorrowerDetail> borrowerDetails) {
        this.borrowerDetails = borrowerDetails;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}

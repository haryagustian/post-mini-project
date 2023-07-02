package org.hary.agustian.entity;

import jakarta.persistence.*;
import org.hary.agustian.util.TimeBase;

import java.io.Serial;
import java.io.Serializable;
import java.time.Year;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends TimeBase implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @Temporal(TemporalType.DATE)
    private Year year;
    private Integer quantity;
    private Integer borrowPrice;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "book", targetEntity = BorrowerDetail.class)
    private List<BorrowerDetail> borrowerDetails;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Author.class)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Publisher.class)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book(){
    }

    public Book(String title, Year year, Integer quantity, Integer borrowPrice, Category category, List<BorrowerDetail> borrowerDetails, List<Author> authors, Publisher publisher) {
        this.title = title;
        this.year = year;
        this.quantity = quantity;
        this.borrowPrice = borrowPrice;
        this.category = category;
        this.borrowerDetails = borrowerDetails;
        this.authors = authors;
        this.publisher = publisher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

package org.hary.agustian.entity;

import jakarta.persistence.*;
import org.hary.agustian.util.TimeBase;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "borrower_details")
public class BorrowerDetail extends TimeBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Book.class)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Borrower.class)
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;

    public BorrowerDetail() {
    }

    public BorrowerDetail(Integer quantity, Book book, Borrower borrower) {
        this.quantity = quantity;
        this.book = book;
        this.borrower = borrower;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }
}

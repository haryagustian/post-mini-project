package org.hary.agustian.model.borrower.detail;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hary.agustian.entity.Book;
import org.hary.agustian.entity.Borrower;

public class BorrowerDetailResponse {
    private Integer id;
    private Integer quantity;
    private Book book;
    private Borrower borrower;

    public BorrowerDetailResponse() {
    }

    public BorrowerDetailResponse(Integer id, Integer quantity, Book book, Borrower borrower) {
        this.id = id;
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

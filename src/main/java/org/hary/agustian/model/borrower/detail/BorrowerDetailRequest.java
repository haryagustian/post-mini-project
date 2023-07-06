package org.hary.agustian.model.borrower.detail;

import org.hary.agustian.entity.Book;
import org.hary.agustian.entity.Borrower;

public class BorrowerDetailRequest {
    private Integer quantity;
    private Book book;
    private Borrower borrower;

    public BorrowerDetailRequest() {
    }

    public BorrowerDetailRequest(Integer quantity, Book book, Borrower borrower) {
        this.quantity = quantity;
        this.book = book;
        this.borrower = borrower;
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

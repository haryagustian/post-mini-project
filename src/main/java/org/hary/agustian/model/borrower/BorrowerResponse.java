package org.hary.agustian.model.borrower;

import jakarta.persistence.*;
import org.hary.agustian.entity.BorrowerDetail;
import org.hary.agustian.entity.User;

import java.util.Date;
import java.util.List;

public class BorrowerResponse {
    private Integer id;
    private Date enterAt;
    private Date backAt;
    private User user;
    private List<BorrowerDetail> borrowerDetails;

    public BorrowerResponse() {
    }

    public BorrowerResponse(Integer id, Date enterAt, Date backAt, User user, List<BorrowerDetail> borrowerDetails) {
        this.id = id;
        this.enterAt = enterAt;
        this.backAt = backAt;
        this.user = user;
        this.borrowerDetails = borrowerDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEnterAt() {
        return enterAt;
    }

    public void setEnterAt(Date enterAt) {
        this.enterAt = enterAt;
    }

    public Date getBackAt() {
        return backAt;
    }

    public void setBackAt(Date backAt) {
        this.backAt = backAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BorrowerDetail> getBorrowerDetails() {
        return borrowerDetails;
    }

    public void setBorrowerDetails(List<BorrowerDetail> borrowerDetails) {
        this.borrowerDetails = borrowerDetails;
    }
}

package org.hary.agustian.entity;

import jakarta.persistence.*;
import org.hary.agustian.util.TimeBase;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "borrowers")
public class Borrower extends TimeBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "enter_at")
    @Temporal(TemporalType.DATE)
    private Date enterAt;

    @Column(name = "back_at")
    @Temporal(TemporalType.DATE)
    private Date backAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "borrower", targetEntity = BorrowerDetail.class)
    private List<BorrowerDetail> borrowerDetails;

    public Borrower() {
    }

    public Borrower(Date enterAt, Date backAt, User user, List<BorrowerDetail> borrowerDetails) {
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

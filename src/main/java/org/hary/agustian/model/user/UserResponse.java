package org.hary.agustian.model.user;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.hary.agustian.entity.Borrower;
import org.hary.agustian.entity.Token;
import org.hary.agustian.entity.User;
import org.hary.agustian.enums.Role;
import org.hary.agustian.enums.Sex;

import java.util.List;

public class UserResponse {
    private Integer id;
    private String fullname;
    private String username;
    private String email;
    private String password;
    private Sex sex;
    private String telephone;
    private String address;
    private Role role;
    private List<Token> tokens;
    private Borrower borrower;

    public UserResponse() {
    }

    public UserResponse(Integer id, String fullname, String username, String email, String password, Sex sex, String telephone, String address, Role role, List<Token> tokens, Borrower borrower) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.telephone = telephone;
        this.address = address;
        this.role = role;
        this.tokens = tokens;
        this.borrower = borrower;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }
}

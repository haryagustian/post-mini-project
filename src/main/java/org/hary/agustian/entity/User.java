package org.hary.agustian.entity;

import jakarta.persistence.*;
import org.hary.agustian.enums.Role;
import org.hary.agustian.enums.Sex;
import org.hary.agustian.util.TimeBase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends TimeBase implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullname;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String telephone;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", targetEntity = Token.class)
    private List<Token> tokens;

    @OneToOne(mappedBy = "user", targetEntity = Borrower.class)
    private Borrower borrower;

    public User() {
    }

    public User(String fullname, String username, String email, String password, Sex sex, String telephone, String address, Role role) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.telephone = telephone;
        this.address = address;
        this.role = role;
    }

    public User(String fullname, String username, String email, String password, Sex sex, String telephone, String address, Role role, List<Token> tokens, Borrower borrower) {
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

    public String getFullName() {
        return fullname;
    }

    public void getFullName(String fullname) {
        this.fullname = fullname;
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
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.simpleGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package org.hary.agustian.model.token;

import org.hary.agustian.entity.User;
import org.hary.agustian.enums.TokenType;

public class TokenResponse {
    private Integer id;
    private String token;
    private final TokenType tokenType = TokenType.BEARER;
    private boolean expired;
    private boolean revoked;
    private User user;

    public TokenResponse() {
    }

    public TokenResponse(Integer id, String token, boolean expired, boolean revoked, User user) {
        this.id = id;
        this.token = token;
        this.expired = expired;
        this.revoked = revoked;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

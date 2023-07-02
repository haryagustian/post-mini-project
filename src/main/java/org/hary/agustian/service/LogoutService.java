package org.hary.agustian.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hary.agustian.entity.Token;
import org.hary.agustian.exception.ResourceNotFoundException;
import org.hary.agustian.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {

    @Autowired
    private TokenRepository tokenRepository;

    public LogoutService() {
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authenticateHeader = request.getHeader("Authorization");

        if (authenticateHeader == null || !authenticateHeader.startsWith("Bearer "))
            throw new ResourceNotFoundException("NOT CONTAINS TYPE : BEARER (Logout)");

        Token token = tokenRepository.findByToken(authenticateHeader.substring(7)).orElse(null);

        if (token != null){
            token.setExpired(true);
            token.setRevoked(true);
            tokenRepository.save(token);
            SecurityContextHolder.clearContext();
        }
    }
}
